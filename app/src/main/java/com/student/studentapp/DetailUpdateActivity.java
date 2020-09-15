package com.student.studentapp;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailUpdateActivity extends AppCompatActivity {

    TextView uTitle,uDescription,upostdate;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_update);

        uTitle=(TextView)findViewById(R.id.atitle);
        uDescription=(TextView)findViewById(R.id.adescription);
        upostdate=(TextView)findViewById(R.id.apostdate);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            uTitle.setText(mBundle.getString("Title"));
            uDescription.setText( mBundle.getString("Description"));
            upostdate.setText("Post Date :"+mBundle.getString("keyValue"));
            key=mBundle.getString("keyValue");

        }
    }
}
