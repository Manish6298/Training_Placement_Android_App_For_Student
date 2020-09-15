package com.student.studentapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Fragment2 extends androidx.fragment.app.Fragment {
    TextView tv6,tv7,tv8,tv9,tv10, tv11,tv12,tv13,tv14,tv15,tv16,tv20,tv21;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment2, container, false);



        tv6 = (TextView)view.findViewById(R.id.t6);
        tv8 = (TextView)view.findViewById(R.id.t8);
        tv9 = (TextView)view.findViewById(R.id.t9);
        tv10 = (TextView)view.findViewById(R.id.t10);
        tv11 = (TextView)view.findViewById(R.id.t11);
        tv12 = (TextView)view.findViewById(R.id.t12);
        tv13 = (TextView)view.findViewById(R.id.t13);
        tv14 = (TextView)view.findViewById(R.id.t14);
        tv15 = (TextView)view.findViewById(R.id.t15);
        tv16 = (TextView)view.findViewById(R.id.t16);
        tv20 = (TextView)view.findViewById(R.id.t20);
        tv21 = (TextView)view.findViewById(R.id.t21);




        tv6.setText("" +getActivity().getIntent().getStringExtra("OBJECTIVE"));
        tv20.setText("City:" +getActivity().getIntent().getStringExtra("EMP"));
        tv8.setText("" +getActivity().getIntent().getStringExtra("JOBD"));
        tv9.setText("Skills: " +getActivity().getIntent().getStringExtra("SKILL"));
        tv10.setText("Experience:  " +getActivity().getIntent().getStringExtra("EXPE")+"year");
        tv11.setText("" +getActivity().getIntent().getStringExtra("CLG"));
        tv12.setText("" +getActivity().getIntent().getStringExtra("DEGREE")+", ");
        tv21.setText("" +getActivity().getIntent().getStringExtra("PASS")+"year");
        tv13.setText("GPA: " +getActivity().getIntent().getStringExtra("GPA")+"%");
        tv14.setText("" +getActivity().getIntent().getStringExtra("AWARD"));
        tv15.setText("Certifications: " +getActivity().getIntent().getStringExtra("CERTIFICATION"));
        tv16.setText("Hobbies: " +getActivity().getIntent().getStringExtra("HOBBIES"));
        return view;
    }
}
