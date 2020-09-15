package com.student.studentapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView sname,semail,seno,smno,sadd,sdob,sgender,sssc,shsc,sdiploma,sfe,sse,ste,sbe,sdeadb,sliveb,shobbies,sintern,sachieve,syear,sbranch;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Progress Dialog Init..

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING ...");

        // Student PROFILE

        semail=(TextView)findViewById(R.id.eemail);
        sname=(TextView)findViewById(R.id.ename);
        seno=(TextView)findViewById(R.id.eenroll);
        smno=(TextView)findViewById(R.id.emn);
        sadd=(TextView)findViewById(R.id.eaddress);
        sdob=(TextView)findViewById(R.id.edob);
        sgender=(TextView)findViewById(R.id.egender);

        // Student EDUCATION

        sssc=(TextView)findViewById(R.id.essc);
        shsc=(TextView)findViewById(R.id.ehsc);
        sdiploma=(TextView)findViewById(R.id.ediploma);
        ste=(TextView)findViewById(R.id.ete);
        sbe=(TextView)findViewById(R.id.ebe);
        sse=(TextView)findViewById(R.id.ese);
        sfe=(TextView)findViewById(R.id.efe);
        sliveb=(TextView)findViewById(R.id.eliveback);
        sdeadb=(TextView)findViewById(R.id.edeadback);
        syear=(TextView)findViewById(R.id.eyears);
        sbranch=(TextView)findViewById(R.id.ebranch);

        // Student Skills

        shobbies=(TextView)findViewById(R.id.ehobby);
        sintern=(TextView)findViewById(R.id.einternship);
        sachieve=(TextView)findViewById(R.id.eachievement_info);


        // FireBase

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("StudentRegister").child(firebaseAuth.getCurrentUser().getUid());

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                // Student PROFILE

                semail.setText(member.getEmail());
                sname.setText(member.getName());
                seno.setText(member.getEno());
                smno.setText(member.getMno());
                sadd.setText(member.getAddress());
                sdob.setText(member.getDate());
                sgender.setText(member.getGender());
                // Student EDUCATION

                sssc.setText(member.getSsc());
                shsc.setText(member.getHsc());
                sdiploma.setText(member.getDiploma());
                ste.setText(member.getTe());
                sbe.setText(member.getBe());
                sse.setText(member.getSe());
                sfe.setText(member.getFe());
                sliveb.setText(member.getLive());
                sdeadb.setText(member.getDead());
                syear.setText(member.getSyear());
                sbranch.setText(member.getSbranch());

                // Student Skills

                shobbies.setText(member.getHobbies());
                sintern.setText(member.getInternship());
                sachieve.setText(member.getAchieve());


                progressDialog.dismiss();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(Profile.this, "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }

    public void updateProfile(View view) {

        startActivity(new Intent(getApplicationContext(),UpdateStudentProfile.class)
                        .putExtra("semail",semail.getText().toString())
                        .putExtra("sname",sname.getText().toString())
                        .putExtra("sbranch",sbranch.getText().toString())
                        .putExtra("syear",syear.getText().toString())
                        .putExtra("smno",smno.getText().toString())
                        .putExtra("sadd",sadd.getText().toString())
                        .putExtra("sdob",sdob.getText().toString())
                        .putExtra("seno",seno.getText().toString())
                        .putExtra("sgender",sgender.getText().toString())

                .putExtra("sssc",sssc.getText().toString())
                .putExtra("shsc",shsc.getText().toString())
                .putExtra("sfe",sfe.getText().toString())
                .putExtra("sse",sse.getText().toString())
                .putExtra("ste",ste.getText().toString())
                .putExtra("sbe",sbe.getText().toString())
                .putExtra("sliveb",sliveb.getText().toString())
                .putExtra("sdeadb",sdeadb.getText().toString())
                .putExtra("sdiploma",sdiploma.getText().toString())

                .putExtra("shobbies",shobbies.getText().toString())
                .putExtra("sintern",sintern.getText().toString())
                .putExtra("sachieve",sachieve.getText().toString())



        );
        finish();

    }
}
