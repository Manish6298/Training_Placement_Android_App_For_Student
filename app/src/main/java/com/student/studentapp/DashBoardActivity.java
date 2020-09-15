package com.student.studentapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoardActivity extends AppCompatActivity {

 // Declaration

    Button btnProfile,btnVideo,btnUpdates,btnQuiz,btnResume,btnLogout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);



        Toolbar toolbar = findViewById(R.id.toolBarDashboard);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

 // Intialization

        btnProfile=(Button)findViewById(R.id.buttonProfile);
        btnVideo=(Button)findViewById(R.id.buttonEdu);
        btnUpdates=(Button)findViewById(R.id.buttonHealth);
        btnQuiz=(Button)findViewById(R.id.buttonGoal);
        btnResume=(Button)findViewById(R.id.buttonFinance);
        btnLogout=(Button)findViewById(R.id.buttonComfort);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

       // Defining Functionality

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashBoardActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(DashBoardActivity.this, "User Successfully LogOut..", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardActivity.this, Videos.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardActivity.this,Profile.class));
            }
        });

        btnUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardActivity.this,Updates.class));
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardActivity.this,Quiz.class));
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardActivity.this,Resume.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.termscondition :
                startActivity(new Intent(getApplicationContext(),TermsCondition.class));
                return true;

            case R.id.AboutApp :
                startActivity(new Intent(getApplicationContext(),About_App.class));
                return true;

            case R.id.rateApp :
                startActivity(new Intent(getApplicationContext(),RatingApp.class));
                return true;

            case R.id.shareApp :

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plan");
                String shareBody="Click this Link and Download Trainning & Placement App : https://www.tour2tech.com ";
                String shareSubject = " Download Trainning & Placement Android App ";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Via"));
                return true;

            case R.id.aboutDeveloper :
                startActivity(new Intent(getApplicationContext(),AboutDevelopers.class));
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure you want to Exit ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DashBoardActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


}
