package com.ex.anchalgarg.somegame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class welcomeScreen extends Fragment {

    Button start;
    Button Exit;
    MainActivity inst;
    //public static int x=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        start = (Button)rootView.findViewById(R.id.Start);
        Exit = (Button)rootView.findViewById(R.id.Exit);
        Log.d("ufeh","____---____---");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MainActivity.x=1;
               // Intent intent = new Intent(getActivity(), MainActivity.class);
               // startActivity(intent);
                inst.play();
                //inst.onCreate(null);
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        return rootView;
    }


}