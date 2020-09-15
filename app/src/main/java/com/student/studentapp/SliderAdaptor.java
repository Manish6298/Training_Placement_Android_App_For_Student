package com.student.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

class SliderAdaptor extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdaptor(Context context)
    {
        this.context =context;
    }

    public int[] slide_image ={
            R.drawable.video,
            R.drawable.quiz,
            R.drawable.resume
    };

    public String[] slide_heading ={
            "Video",
            "Quiz",
            "Resume"
    };

    public String[] slide_desc ={
            "Surf videos across youtube. Get Videos in Realtime ...",
            "Test Yourself by playing quiz",
            "Build Your Resume in Seconds ! Experience a new way of making a resume"
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        View view = layoutInflater.inflate( R.layout.slidelayout, container, false );

        ImageView img = (ImageView) view.findViewById( R.id.imageView );
        TextView heading = (TextView) view.findViewById( R.id.heading );
        TextView desc = (TextView) view.findViewById( R.id.description );

        img.setImageResource( slide_image[position] );
        heading.setText( slide_heading[position] );
        desc.setText( slide_desc[position] );

        container.addView( view );
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object)
    {
        container.removeView( (RelativeLayout)object );
    }
}
