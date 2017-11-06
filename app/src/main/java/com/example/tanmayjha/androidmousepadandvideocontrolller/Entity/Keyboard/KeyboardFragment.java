package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Keyboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary.ConnectionService;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

import java.io.PrintWriter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyboardFragment extends Fragment {
    Button send;
    EditText stringToBePrinted;
    private boolean isConnected = ConnectionService.connectionService.isConnected();
    private PrintWriter out=ConnectionService.connectionService.getOut();


    public KeyboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        send=(Button)view.findViewById(R.id.send);
        stringToBePrinted=(EditText) view.findViewById(R.id.text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected && out != null) {
                    out.println(stringToBePrinted.getText().toString());
                }
            }
        });
    }

}
