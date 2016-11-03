package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.SystemSettings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary.ConnectionService;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.Constants;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

import java.io.PrintWriter;
import java.io.StringReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemSettingsFragment extends Fragment {

    Button shutDown,restart,sleep,brightnessUp,brightnessDown;
    private boolean isConnected = ConnectionService.connectionService.isConnected();
    private PrintWriter out=ConnectionService.connectionService.getOut();


    public SystemSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system_settings, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        shutDown=(Button)view.findViewById(R.id.shut_down_button);
        restart=(Button)view.findViewById(R.id.restart_button);
        sleep=(Button)view.findViewById(R.id.sleep_button);
        brightnessUp=(Button)view.findViewById(R.id.brightness_up_button);
        brightnessDown=(Button)view.findViewById(R.id.brightness_down_button);
        shutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(Constants.SHUT_DOWN);
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(Constants.RESTART);
                }
            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(Constants.SLEEP);
                }
            }
        });
        brightnessUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(Constants.BRIGHTNESS_UP);
                }
            }
        });
        brightnessDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(Constants.BRIGHTNESS_DOWN);
                }
            }
        });

    }
}
