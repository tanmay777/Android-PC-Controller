package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.Constants;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button playPauseButton, forwardButton, backwardButton;
    TextView mousePad;

    private boolean isConnected = false;
    private boolean mouseMoved = false;
    private Socket socket;
    private PrintWriter out;

    private float initX = 0;
    private float initY = 0;
    private float disX = 0;
    private float disY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playPauseButton = (Button) findViewById(R.id.play_pause_button);
        forwardButton = (Button) findViewById(R.id.forward_button);
        backwardButton = (Button) findViewById(R.id.backward_button);

        playPauseButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        backwardButton.setOnClickListener(this);

        mousePad = (TextView) findViewById(R.id.mouse_pad);

        //To Capture finger taps and movement of the textView
        mousePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isConnected && out != null) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            //To save X and Y positions when the user touches the TextViiew
                            //!!But we didn't send the location
                            initX = motionEvent.getX();
                            initY = motionEvent.getY();
                            mouseMoved = false;
                            break;
                        }
                        case MotionEvent.ACTION_MOVE: {
                            disX = motionEvent.getX() - initX;//Mouse movement in X direction
                            disY = motionEvent.getY() - initY;//Mouse movement in Y direction
                            /*set init to new position so that continuous mouse movement
                            is captured*/
                            initX = motionEvent.getX();
                            initY = motionEvent.getY();
                            if (disX != 0 || disY != 0) {
                                out.println(disX + "," + disY);
                            }
                            mouseMoved = true;
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            //consider a tap only if usr did not move mouse after ACTION_DOWN
                            //!!THIS LOGIC IS NOT QUITE CLEAR
                            if (!mouseMoved) {
                                out.println(Constants.MOUSE_LEFT_CLICK);
                            }
                            break;
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //!!When is this fuction called? Is it called everytime we press the icon?
        if (id == R.id.action_id) {
            ConnectPhoneTask connectPhoneTask = new ConnectPhoneTask();
            connectPhoneTask.execute(Constants.SERVER_IP);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_pause_button:
                if (isConnected && out != null) {
                    out.println(Constants.PLAY);
                }
                break;
            case R.id.forward_button:
                if(isConnected &&out!=null) {
                    out.println(Constants.FORWARD);
                }
                break;
            case R.id.backward_button:
                if (isConnected && out != null) {
                    out.println(Constants.BACKWARD);
                }
                break;
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(isConnected && out!=null){
            try {
                out.println("exit"); //tell server to exit
                socket.close();
            }
            catch (IOException e){
                Log.e("Android App ","Error in closing the socket",e);
                //!!Aren't there supposed to be only 2 arguments? May be because of the size of each argument
            }
        }
    }

    public class ConnectPhoneTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... params){
            boolean result=true;
            try{
                //public static InetAddress getByName(String host) throws UnknownHostException.
                // Determines the IP address of a host, given the host's name.
                // The host name can either be a machine name, such as " java.sun.com ",
                // or a textual representation of its IP address.
                InetAddress serverAddr=InetAddress.getByName(params[0]);
                Log.v("Check params[0]:",params[0]);
                socket=new Socket(serverAddr,Constants.SERVER_PORT); //Opens socket on server IP and port
            }
            catch (IOException e){
                Log.e("Android App ","Error while connecting",e);
                result=false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            isConnected=result;
            Toast.makeText(getApplicationContext(),isConnected?"Connected to server!":"Error while connecting",Toast.LENGTH_LONG).show();
            //there is an if statement in 2nd argument of toast
            try{
                if(isConnected){
                    out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                    //create output stream to send data to server.
                }
                } catch (IOException e){
                Log.e("Android App","Error while creating OutWriter",e);
                Toast.makeText(getApplicationContext(),"Error while connecting",Toast.LENGTH_LONG).show();
            }
        }
    }


}



