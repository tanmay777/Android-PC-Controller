package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Mouse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary.ConnectionService;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.Constants;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple {@link Fragment} subclass.
 */
public class MouseFragment extends Fragment {

    private boolean isConnected = ConnectionService.connectionService.isConnected;
    private boolean mouseMoved = false;
    private PrintWriter out=ConnectionService.connectionService.getOut();
    TextView mousePad;

    private float initX = 0;
    private float initY = 0;
    private float disX = 0;
    private float disY = 0;


    public MouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mouse, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        mousePad = (TextView)view.findViewById(R.id.mouse_pad);
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

}
