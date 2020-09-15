package com.student.studentapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroActivity extends AppCompatActivity {

    private ViewPager slide;
    private LinearLayout llayout;
    private TextView[] dots;
    private SliderAdaptor sliderAdaptor;
    private Button back, next;
    private int current;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intro );

        firebaseAuth = FirebaseAuth.getInstance();

        slide = (ViewPager) findViewById( R.id.viewpager );
        llayout = (LinearLayout) findViewById( R.id.llayout );

        back = (Button) findViewById( R.id.back );
        next = (Button) findViewById( R.id.next );

        sliderAdaptor = new SliderAdaptor( this );
        slide.setAdapter( sliderAdaptor );
        addDotsIndicator( 0 );
        slide.addOnPageChangeListener( viewListner );
        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide.setCurrentItem( current + 1 );

            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide.setCurrentItem( current - 1 );
            }
        } );

    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        llayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView( this );
            dots[i].setText( Html.fromHtml( "&#8226;" ) );
            dots[i].setTextSize( 40 );
            dots[i].setTextColor( getResources().getColor( R.color.colorTransparentWhite ) );
            llayout.addView( dots[i] );
        }

        if (dots.length > 0) {
            dots[position].setTextColor( getResources().getColor( R.color.colorWhite ) );
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator( i );
            current = i;

            if (i == 0) {
                next.setEnabled( true );
                back.setEnabled( false );
                back.setVisibility( View.INVISIBLE );

                next.setText( "Next" );
                back.setText( "" );
            } else if (i == dots.length - 1) {
                next.setEnabled( true );
                back.setEnabled( true );
                back.setVisibility( View.INVISIBLE );
                back.setText( "Back" );
                next.setText( "Finish" );
                next.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( IntroActivity.this, MainActivity.class );
                        finish();
                        startActivity( intent );
                       // Toast.makeText( IntroActivity.this, "new activity", Toast.LENGTH_SHORT ).show();

                    }
                } );

            } else {
                next.setEnabled( true );
                back.setEnabled( true );
                back.setVisibility( View.VISIBLE );

                next.setText( "Next" );
                back.setText( "Back" );
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI( currentUser );
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // User is signed in
            Intent i = new Intent( IntroActivity.this, DashBoardActivity.class );
            i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( i );
        } else {

        }
    }
}