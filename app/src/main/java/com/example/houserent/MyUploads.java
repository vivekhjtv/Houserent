package com.example.houserent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.example.adicionalesprueba.R;
//import com.example.adicionalesprueba.R


import java.util.ArrayList;

public class MyUploads extends AppCompatActivity {


    public RecyclerView recyclerView;
    public MyHouseadapter myHouseadapter;
    public DatabaseReference databaseReference;
    public ProgressBar progressBar;
    public ArrayList<UploadDetails> uploadDetails;
    public FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_uploads);
        recyclerView=findViewById(R.id.rv);
        progressBar=findViewById(R.id.progress);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadDetails = new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getCurrentUser().getUid()).child("house");
        getSupportActionBar().setTitle("My Uploads");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // System.out.println("In myuploads");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    UploadDetails ud = postSnapShot.getValue(UploadDetails.class);
                    //System.out.println("House details"+uploadDetails);
                    //String avail=uploadDetails.getAvailable().toString();
                    //System.out.println("Available"+avail);
                    //if(avail.equals("Yes")) {
                        //System.out.println("HERE"+avail);
                    System.out.println("HERE"+ud.getListed());
                        uploadDetails.add(ud);
                    }

                myHouseadapter = new MyHouseadapter(MyUploads.this,uploadDetails);
                recyclerView.setAdapter(myHouseadapter);
                progressBar.setVisibility(View.INVISIBLE);
                    //mUploadDetails=new UploadDetails();
                    //mUploadDetails=uploadDetails;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyUploads.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.profile){
            startActivity(new Intent(this,Profile.class));

            return true;
        }
        if(id==R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }
        if(id==android.R.id.home)
        {
            Intent intent=new Intent(MyUploads.this,MainActivity.class);
            startActivity(intent);
        }
        return true;
}
}
