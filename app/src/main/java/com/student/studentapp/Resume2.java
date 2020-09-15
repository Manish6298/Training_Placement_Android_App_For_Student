package com.student.studentapp;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Resume2 extends AppCompatActivity {

    private static final int STORAGE_CODE = 1000;
    private FloatingActionButton btn;
    private CardView llPdf;
    private Bitmap bitmap;
    TextView tv1,tv7;
    private int REQUEST_PERMISSIONS;
    String dirpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume2);

        btn = (FloatingActionButton)findViewById(R.id.pdf);
        llPdf = (CardView) findViewById(R.id.cardview);


        tv1 = (TextView) findViewById( R.id.t1 );
        tv7 = (TextView) findViewById( R.id.t7 );
        tv1.setText( "" + getIntent().getStringExtra( "NAME" ) );
        tv7.setText( "" + getIntent().getStringExtra( "JOBT" ) );



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_DENIED)
                    {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions( permission, STORAGE_CODE );

                    }else {
                        //Permission Granted
                        try {
                            Log.d("size"," "+llPdf.getWidth() +"  "+llPdf.getHeight());
                            bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
                            createPdf();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    //Runtime for Above Marshmallow
                    try {
                        Log.d("size"," "+llPdf.getWidth() +"  "+llPdf.getHeight());
                        bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
                        createPdf();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf() throws FileNotFoundException{
        WindowManager wm = (WindowManager) getSystemService( Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float height = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHeight = (int) height, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);

        paint.setColor( Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/pdf.pdf";
        File filePath;
        filePath = new File( Environment.getExternalStorageDirectory() + File.separator + "pdf.pdf");;
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "pdf.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(Resume2.this, BuildConfig.APPLICATION_ID + ".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(Resume2.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {

                if (requestCode == REQUEST_PERMISSIONS) {

                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        try {
                            createPdf();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText( getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG ).show();

                    }
                }
            }
        }
    }

}
