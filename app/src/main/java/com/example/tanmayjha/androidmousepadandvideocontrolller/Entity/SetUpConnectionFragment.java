package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanmayjha.androidmousepadandvideocontrolller.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetUpConnectionFragment extends Fragment {

    EditText IPAdress,portNumber;
    TextInputLayout inputLayoutIPAdress,inputLayoutPortNumber;
    Button done;

    public SetUpConnectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_up_connection, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        done=(Button)view.findViewById(R.id.done_button);
        inputLayoutIPAdress=(TextInputLayout)view.findViewById(R.id.textlayout_ip_address);
        inputLayoutPortNumber=(TextInputLayout)view.findViewById(R.id.textlayout_port_number);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPAdress=(EditText)view.findViewById(R.id.ip_address);
                portNumber=(EditText)view.findViewById(R.id.port_number);
                if(IPAdress.getText().toString().trim().isEmpty()){
                    inputLayoutIPAdress.setError("Enter IP address");
                    IPAdress.requestFocus();
                }
                if(portNumber.getText().toString().trim().isEmpty()){
                    inputLayoutPortNumber.setError("Enter Port Number");
                    portNumber.requestFocus();
                }
            }
        });
    }

}
