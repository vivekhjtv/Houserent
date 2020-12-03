package com.example.houserent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView firstname,lastname,email,mobileno;
    private TextInputEditText address;
    private Button edit,MyUpload;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstname=findViewById(R.id.fname);
        lastname=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobileno=findViewById(R.id.mnum);
        edit=findViewById(R.id.editbtn);
        MyUpload=findViewById(R.id.myupload);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DatabaseReference databaseReference=firebaseDatabase.getReference();
        databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Userprofile userprofile = dataSnapshot.getValue(Userprofile.class);
                    System.out.println("First name"+ userprofile.getFirstName());
                    firstname.setText("FirstName:" + userprofile.getFirstName());
                    lastname.setText("LastName:" + userprofile.getLastName());
                    email.setText("Email:" + userprofile.getTxtEmail());
                    mobileno.setText("Mobile No:" + userprofile.getMobileNumber());

                }
                catch(NullPointerException nE){
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(Profile.this, "Sorry, We are unable to fetch data !", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,updateprofile.class));
            }
        });

        MyUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,MyUploads.class));
            }
        });
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
            Intent intent=new Intent(Profile.this,MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
