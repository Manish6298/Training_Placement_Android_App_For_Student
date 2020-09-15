package com.student.studentapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateStudentProfile extends AppCompatActivity {

    EditText sname,seno,smno,sadd,sdob,sssc,shsc,sdiploma,sfe,sse,ste,sbe,sdeadb,sliveb,shobbies,sintern,sachieve;
    TextView semail,sgender;
    //--------------- Spinner -----------------

    Spinner yearspinner,branchspinner;

    Member ad;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_profile);

        // Progress Dialog Init..

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Profile ...");

        // Class Object Creation

        ad=new Member();

        // Student PROFILE

        semail=(TextView)findViewById(R.id.eemail);
        sname=(EditText) findViewById(R.id.ename);
        seno=(EditText)findViewById(R.id.eenroll);
        smno=(EditText)findViewById(R.id.emn);
        sadd=(EditText)findViewById(R.id.eaddress);
        sdob=(EditText)findViewById(R.id.edob);
        sgender=(TextView) findViewById(R.id.egender);

        // Student EDUCATION

        sssc=(EditText)findViewById(R.id.essc);
        shsc=(EditText)findViewById(R.id.ehsc);
        sdiploma=(EditText)findViewById(R.id.ediploma);
        ste=(EditText)findViewById(R.id.ete);
        sbe=(EditText)findViewById(R.id.ebe);
        sse=(EditText)findViewById(R.id.ese);
        sfe=(EditText)findViewById(R.id.efe);
        sliveb=(EditText)findViewById(R.id.eliveback);
        sdeadb=(EditText)findViewById(R.id.edeadback);
        yearspinner=(Spinner)findViewById(R.id.eyearSpinner);
        branchspinner=(Spinner)findViewById(R.id.ebranchSpinner);

        // Student Skills

        shobbies=(EditText)findViewById(R.id.ehobby);
        sintern=(EditText)findViewById(R.id.einternship);
        sachieve=(EditText)findViewById(R.id.eachievement_info);

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("StudentRegister").child(firebaseAuth.getCurrentUser().getUid());

        progressDialog.show();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {

            progressDialog.dismiss();

            branchspinner.setSelected(false);
            yearspinner.setSelected(false);

            // Student PROFILE

            semail.setText(bundle.getString("semail"));
            sname.setText(bundle.getString("sname"));
            seno.setText(bundle.getString("seno"));
            smno.setText(bundle.getString("smno"));
            sadd.setText(bundle.getString("sadd"));
            sdob.setText(bundle.getString("sdob"));
            sgender.setText(bundle.getString("sgender"));

            // Student EDUCATION

            sssc.setText(bundle.getString("sssc"));
            shsc.setText(bundle.getString("shsc"));
            sdiploma.setText(bundle.getString("sdiploma"));
            ste.setText(bundle.getString("ste"));
            sbe.setText(bundle.getString("sbe"));
            sse.setText(bundle.getString("sse"));
            sfe.setText(bundle.getString("sfe"));
            sliveb.setText(bundle.getString("sliveb"));
            sdeadb.setText(bundle.getString("sdeadb"));

            // Student Skills

            shobbies.setText(bundle.getString("shobbies"));
            sintern.setText(bundle.getString("sintern"));
            sachieve.setText(bundle.getString("sachieve"));

        }
        else Toast.makeText(this, "Record Not Found", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    public void updateStudentProfile(View view) {

        String year= yearspinner.getSelectedItem().toString();
        String branch= branchspinner.getSelectedItem().toString();
        progressDialog.show();

        // Profile

        ad.setName(sname.getText().toString());
        ad.setEmail(semail.getText().toString());
        ad.setEno(seno.getText().toString());
        ad.setMno(smno.getText().toString());
        ad.setDate(sdob.getText().toString());
        ad.setAddress(sadd.getText().toString());
        ad.setGender(sgender.getText().toString());

        // Education

        ad.setSbranch(branch);
        ad.setSyear(year);
        ad.setFe(sfe.getText().toString());
        ad.setSe(sse.getText().toString());
        ad.setTe(ste.getText().toString());
        ad.setBe(sbe.getText().toString());
        ad.setSsc(sssc.getText().toString());
        ad.setHsc(shsc.getText().toString());
        ad.setDiploma(sdiploma.getText().toString());
        ad.setLive(sliveb.getText().toString());
        ad.setDead(sdeadb.getText().toString());

        // Other Skills

        ad.setHobbies(shobbies.getText().toString());
        ad.setInternship(sintern.getText().toString());
        ad.setAchieve(sachieve.getText().toString());

        databaseReference.setValue(ad);
        progressDialog.dismiss();
        Toast.makeText(this, "Profile Updated Successfully..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Profile.class));
        finish();

    }
}
