package com.example.houserent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateprofile extends AppCompatActivity {

    private EditText newFirstName , newLastName, newEmail, newNumber;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        newFirstName=findViewById(R.id.editfirstname);
        newLastName=findViewById(R.id.editlastname);
        newEmail=findViewById(R.id.editmail);
        newNumber=findViewById(R.id.editmobilenumber);
        save=findViewById(R.id.updatebtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference=firebaseDatabase.getReference();
        databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Userprofile userprofile=dataSnapshot.getValue(Userprofile.class);
                newFirstName.setText(userprofile.getFirstName());
                newLastName.setText(userprofile.getLastName());
                newEmail.setText(userprofile.getTxtEmail());
                newNumber.setText(userprofile.mobileNumber);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(updateprofile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname=newFirstName.getText().toString();
                String lastname=newLastName.getText().toString();
                String email=newEmail.getText().toString();
                String number=newNumber.getText().toString();

                Userprofile userprofile=new Userprofile(firstname,lastname,email,number);
                databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userprofile);
                finish();
               // startActivity(new Intent(updateprofile.this,updateprofile.class));
            }
        });

    }

}
