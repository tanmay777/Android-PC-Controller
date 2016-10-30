package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Player;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary.ConnectionService;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Control.Constants;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

import java.io.PrintWriter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener{

    Button playPauseButton, forwardButton, backwardButton,volumeUp,volumeDown;
    private boolean isConnected =ConnectionService.connectionService.isConnected();
    private PrintWriter out=ConnectionService.connectionService.getOut();

    public PlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        playPauseButton = (Button) view.findViewById(R.id.play_pause_button);
        forwardButton = (Button) view.findViewById(R.id.forward_button);
        backwardButton = (Button) view.findViewById(R.id.backward_button);
        volumeUp=(Button) view.findViewById(R.id.vol_up_button);
        volumeDown=(Button) view.findViewById(R.id.vol_down_button);

        playPauseButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        backwardButton.setOnClickListener(this);
        volumeUp.setOnClickListener(this);
        volumeDown.setOnClickListener(this);


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
            case R.id.vol_up_button:
                if (isConnected&& out!=null){
                    out.println(Constants.VOLUMEUP);
                }
                break;
            case R.id.vol_down_button:
                if (isConnected&&out!=null)
                {
                    out.println(Constants.VOLUMEDOWN);
                }
        }
    }

}
