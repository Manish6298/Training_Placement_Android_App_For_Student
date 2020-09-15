package com.student.studentapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Updates extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    List<UpdateData> myUpdateList;

    ProgressDialog progressDialog;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        //  ToolBar

        Toolbar toolbar = findViewById(R.id.toolBarUpdates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin Updates");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Updates.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING ...");


        myUpdateList=new ArrayList<>();

        final MyAdapterUpload myAdapterUpload = new MyAdapterUpload(Updates.this,myUpdateList);
        mRecyclerView.setAdapter(myAdapterUpload);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Updates");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myUpdateList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    UpdateData updateData = itemSnapshot.getValue(UpdateData.class);
                    updateData.setKey(itemSnapshot.getKey());
                    myUpdateList.add(updateData);

                }

                myAdapterUpload.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }

        });

    }
}
