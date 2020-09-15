package com.student.studentapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

// Calender

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;
    EditText name,email,pass,repass,eno,emno,add,ssc,hsc,diploma,fe,se,te,be,deadb,liveb,hobbies,intern,achieve;      //Declaration
    Button save;
    DatabaseReference ref;
    Member m;

    RadioButton radioGenderMale,radioGenderFemale;

    // --------------- Calender -----------------
    private static final String TAG="RegisterActivity";
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
   // ---------------- Calender -----------------


    //--------------- Spinner -----------------

    Spinner yearspinner,branchspinner;

    //---------------- Spinner ----------------


    //-------------- Firebase ---------------

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    // ------------- Progress Dialog ----------

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        firebaseAuth = FirebaseAuth.getInstance();

        storedata();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void storedata()
    {

        // Progress Dialog Init

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("REGISTERING ...");

        //Profile Details

        name =(EditText)findViewById(R.id.ename);   //Defination
        email=(EditText)findViewById(R.id.eemail);
        pass=(EditText)findViewById(R.id.epassword);
        repass=(EditText)findViewById(R.id.erepassword);
        eno=(EditText)findViewById(R.id.eenroll);
        emno=(EditText)findViewById(R.id.emn);
        add=(EditText)findViewById(R.id.eaddress);
        radioGenderMale=(RadioButton)findViewById(R.id.male_radio_btn);
        radioGenderFemale=(RadioButton)findViewById(R.id.female_radio_btn);
        save=(Button)findViewById(R.id.submit);

        // Calender Intialization

        DisplayDate =(TextView)findViewById(R.id.edob);
        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(RegisterActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yy:" + day + "/" + month + "/" + year);
                String date= day + "/" + month + "/" + year ;
                DisplayDate.setText(date);

            }
        };


        //Qualification Details

        ssc =(EditText)findViewById(R.id.essc);
        hsc =(EditText)findViewById(R.id.ehsc);
        diploma =(EditText)findViewById(R.id.ediploma);
        fe =(EditText)findViewById(R.id.efe);
        se =(EditText)findViewById(R.id.ese);
        te =(EditText)findViewById(R.id.ete);
        be =(EditText)findViewById(R.id.ebe);
        deadb =(EditText)findViewById(R.id.edeadback);
        liveb=(EditText)findViewById(R.id.eliveback);
        yearspinner = (Spinner) findViewById((R.id.yearSpinner));
        branchspinner = (Spinner) findViewById((R.id.branchSpinner));

        //Other Skills Details

        hobbies =(EditText)findViewById(R.id.ehobby);
        intern=(EditText)findViewById(R.id.einternship);
        achieve =(EditText)findViewById(R.id.eachievement_info);

        // Class Object
        m=new Member();

        // Firebase Database Refrence

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();


        // Button Save Functionality

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

              //Local Variable to store Password

                String password=pass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();
                String gender="";

               // Profile Data Store


                m.setName(name.getText().toString());
                m.setEmail(email.getText().toString());
                m.setEno(eno.getText().toString());
                m.setMno(emno.getText().toString());
                m.setAddress(add.getText().toString().trim());
                m.setDate(DisplayDate.getText().toString());


                if(radioGenderMale.isChecked()){
                    gender="Male";
                }

                if(radioGenderFemale.isChecked()) {
                    gender="Female";
                }

                m.setGender(gender);

                // Education Data Store

                String year= yearspinner.getSelectedItem().toString();
                String branch= branchspinner.getSelectedItem().toString();

                m.setSsc(ssc.getText().toString());
                m.setHsc(hsc.getText().toString());
                m.setDiploma(diploma.getText().toString());
                m.setFe(fe.getText().toString());
                m.setSe(se.getText().toString());
                m.setTe(te.getText().toString());
                m.setBe(be.getText().toString());
                m.setLive(liveb.getText().toString());
                m.setDead(deadb.getText().toString());
                m.setSbranch(branch);
                m.setSyear(year);


                // Skill Data Store

                m.setHobbies(hobbies.getText().toString().trim());
                m.setInternship(intern.getText().toString().trim());
                m.setAchieve(achieve.getText().toString().trim());


                // Validate Data

                // Full name

                if(TextUtils.isEmpty(m.name)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Full Name ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                // Email
                if(TextUtils.isEmpty(m.email)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // password

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // repassword

                if(TextUtils.isEmpty(repassword)){

                    Toast.makeText(RegisterActivity.this, "Please Enter RePassword", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(password.length()<6){

                    Toast.makeText(RegisterActivity.this, "Password Must be more than 6 digit & less than 1 digit", Toast.LENGTH_SHORT).show();
                }


                // Enrollment Number

                if(TextUtils.isEmpty(m.eno)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Enrollment Number ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(m.eno.length() < 8){

                    Toast.makeText(RegisterActivity.this, "Enrollment Number must be greater than 8 digit ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // Mobile Number

                if(TextUtils.isEmpty(m.mno)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(m.mno.length()<10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be more 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                if(m.mno.length()>10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be less than 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                // Address

                if(TextUtils.isEmpty(m.address)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Full Address ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // D.O.B

                if(TextUtils.isEmpty(m.date)){

                    Toast.makeText(RegisterActivity.this, "Please select Date of Birth ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
     /*
                // SSC Marks

                if(TextUtils.isEmpty(m.ssc)){

                    Toast.makeText(RegisterActivity.this, "Please Enter SSC Marks ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // HSC Marks

                if(TextUtils.isEmpty(m.hsc)){

                    m.setHsc("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // Diploma Marks

                if(TextUtils.isEmpty(m.diploma)){

                    m.setDiploma("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // F.E

                if(TextUtils.isEmpty(m.fe)){

                    m.setFe("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // S.E

                if(TextUtils.isEmpty(m.se)){

                    m.setSe("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // T.E

                if(TextUtils.isEmpty(m.te)){

                    m.setTe("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // B.E

                if(TextUtils.isEmpty(m.be)){

                    m.setBe("N/A");
                    progressDialog.dismiss();
                    return;
                }


                // Dead Back

                if(TextUtils.isEmpty(m.dead)){

                    m.setDead("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // Live Back

                if(TextUtils.isEmpty(m.live)){

                    m.setLive("N/A");
                    progressDialog.dismiss();
                    return;
                }


                // Internship Info

                if(TextUtils.isEmpty(m.internship)){

                    m.setInternship("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // Achievement Info

                if(TextUtils.isEmpty(m.hobbies)){

                    m.setHobbies("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // Hobbies

                if(TextUtils.isEmpty(m.hobbies)){

                    m.setHobbies("N/A");
                    progressDialog.dismiss();
                    return;
                }

                // Validation Done !!


      */
                if(password.equals(repassword)){

                    firebaseAuth.createUserWithEmailAndPassword(m.email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete( Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        myRef.child("StudentRegister").child(firebaseAuth.getCurrentUser().getUid()).setValue(m);

                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                    } else {
                                        String msg= task.getException().toString();
                                        Toast.makeText(RegisterActivity.this, "Error:"+msg, Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                    // ...
                                }
                            });

                }

            }
        });


    }

}
