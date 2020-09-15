package com.student.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Resume extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15,et16,et17,et18,et19,et20,et21;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // Toolbar


        Toolbar toolbar = findViewById(R.id.toolBarResume);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText5);
        et4 = (EditText)findViewById(R.id.editText6);
        et5 = (EditText)findViewById(R.id.editText7);
        et6 = (EditText)findViewById(R.id.editText8);
        et7 = (EditText)findViewById(R.id.editText9);
        et8 = (EditText)findViewById(R.id.editText10);
        et9 = (EditText)findViewById(R.id.editText11);
        et10 = (EditText)findViewById(R.id.editText12);
        et11 = (EditText)findViewById(R.id.editText13);
        et12 = (EditText)findViewById(R.id.editText14);
        et13 = (EditText)findViewById(R.id.editText15);
        et14 = (EditText)findViewById(R.id.editText16);
        et15 = (EditText)findViewById(R.id.editText17);
        et16 = (EditText)findViewById(R.id.editText18);
        et17 = (EditText)findViewById(R.id.editText19);
        et18 = (EditText)findViewById(R.id.editText20);
        et19 = (EditText)findViewById(R.id.editText21);
        et20 = (EditText)findViewById(R.id.editText22);
        et21 = (EditText)findViewById(R.id.editText23);
        btn = (FloatingActionButton) findViewById(R.id.b1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Resume.this, Resume2.class);
                String value1 = et1.getText().toString();
                if (value1.length() == 0) {
                    et1.setError("Please enter your Name");
                } else {
                    intent.putExtra("NAME", value1);
                }


                String val2 = et2.getText().toString();
                if (val2.length() == 0) {
                    et2.setError("Please enter Date of Birth");
                } else {
                    intent.putExtra("DOB", val2);
                }



                String val3 = et3.getText().toString();
                if (val3.length() == 0) {
                    et3.setError("please enter your Email");
                } else {
                    intent.putExtra("EMAIL", val3);
                }


                String val4 = et4.getText().toString();
                if (val4.length() == 0) {
                    et4.setError("please enter your Phone number");
                } else {
                    intent.putExtra("PHONE", val4);
                }


                String val5 = et5.getText().toString();
                if (val5.length() == 0) {
                    et5.setError("please enter your Address");
                } else {
                    intent.putExtra("ADDRESS", val5);
                }


                String val6 = et6.getText().toString();
                if (val6.length() == 0) {
                    et6.setError("please enter your City");
                } else {
                    intent.putExtra("CITY", val6);
                }

                String val7 = et7.getText().toString();
                if (val7.length() == 0) {
                    et7.setError("please enter your State");
                } else {
                    intent.putExtra("STATE", val7);
                }

                String val8 = et8.getText().toString();
                if (val8.length() == 0) {
                    et8.setError("please enter your Pincode");
                } else {
                    intent.putExtra("PINCODE", val8);
                }

                String val9 = et9.getText().toString();
                if (val9.length() == 0) {
                    et9.setError("please enter your Objective");
                } else {
                    intent.putExtra("OBJECTIVE", val9);
                }

                String val10 = et10.getText().toString();
                if (val10.length() == 0) {
                    et10.setError("please enter your Job Title");
                } else {
                    intent.putExtra("JOBT", val10);
                }

                String val11 = et11.getText().toString();
                if (val11.length() == 0) {
                    et11.setError("please enter Employer");
                } else {
                    intent.putExtra("EMP", val11);
                }

                String val12 = et12.getText().toString();
                if (val12.length() == 0) {
                    et12.setError("please enter your Job Description");
                } else {
                    intent.putExtra("JOBD", val12);
                }

                String val13 = et13.getText().toString();
                if (val13.length() == 0) {
                    et13.setError("please enter your Skills");
                } else {
                    intent.putExtra("SKILL", val13);
                }

                String val14 = et14.getText().toString();
                if (val14.length() == 0) {
                    et14.setError("please enter your Experience");
                } else {
                    intent.putExtra("EXPE", val14);
                }

                String val15 = et15.getText().toString();
                if (val15.length() == 0) {
                    et15.setError("please enter College Name");
                } else {
                    intent.putExtra("CLG", val15);
                }

                String val16 = et16.getText().toString();
                if (val16.length() == 0) {
                    et16.setError("please enter Degree/Graduation");
                } else {
                    intent.putExtra("DEGREE", val16);
                }


                String val17 = et17.getText().toString();
                if (val17.length() == 0) {
                    et17.setError("please enter year of Passing");
                } else {
                    intent.putExtra("PASS", val17);
                }


                String val18 = et18.getText().toString();
                if (val18.length() == 0) {
                    et18.setError("please enter your GPA");
                } else {
                    intent.putExtra("GPA", val18);
                }

                String val19 = et19.getText().toString();
                if (val19.length() == 0) {
                    et19.setError("please enter your Awards");
                } else {
                    intent.putExtra("AWARD", val19);
                }



                String val20 = et20.getText().toString();
                if (val20.length() == 0) {
                    et20.setError("please enter your Certifications");
                } else {
                    intent.putExtra("CERTIFICATION", val20);
                }



                String val21 = et21.getText().toString();
                if (val21.length() == 0) {
                    et21.setError("please enter your Hobbies");
                } else {
                    intent.putExtra("HOBBIES", val21);
                }
                startActivity(intent);
            }
        });


    }
}
