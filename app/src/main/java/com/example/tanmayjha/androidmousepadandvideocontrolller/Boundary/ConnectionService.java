package com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.Constants;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.NetworkValues;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by tanmay jha on 27-10-2016.
 */

public class ConnectionService extends Service {
    public boolean isConnected = false;
    public Socket socket;
    public PrintWriter out;


    public static ConnectionService connectionService=new ConnectionService();

    public PrintWriter getOut() {
        return out;
    }

    public boolean isConnected() {

        return isConnected;
    }



    public ConnectionService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boolean result=true;
        try{
            //public static InetAddress getByName(String host) throws UnknownHostException.
            // Determines the IP address of a host, given the host's name.
            // The host name can either be a machine name, such as " java.sun.com ",
            // or a textual representation of its IP address.
            InetAddress serverAddr=InetAddress.getByName(NetworkValues.networkValues.getServerIP());
            Log.v("IP address value:",NetworkValues.networkValues.getServerIP());
            socket=new Socket(serverAddr, NetworkValues.networkValues.getServerPort()); //Opens socket on server IP and port
        }
        catch (IOException e){
            Log.e("Android App ","Error while connecting",e);
            result=false;
        }

        connectionService.isConnected=result;
        Toast.makeText(getApplicationContext(),isConnected?"Connected to server!":"Error while connecting",Toast.LENGTH_LONG).show();
        //there is an if statement in 2nd argument of toast
        try{
            if(isConnected){
                connectionService.out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                //create output stream to send data to server.
            }
        } catch (IOException e){
            Log.e("Android App","Error while creating OutWriter",e);
            Toast.makeText(getApplicationContext(),"Error while connecting",Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(connectionService.isConnected && connectionService.out!=null){
            try {
                connectionService.out.println("exit"); //tell server to exit
                connectionService.socket.close();
            }
            catch (IOException e){
                Log.e("Android App ","Error in closing the socket",e);
                //!!Aren't there supposed to be only 2 arguments? May be because of the size of each argument
            }
        }
    }
}
