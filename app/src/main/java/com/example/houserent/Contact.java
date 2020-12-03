package com.example.houserent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contact extends AppCompatActivity {
    private TextView contect, to, subject, message;
    private EditText memail, msubject, msg, mobile_no;
    private Button send_email;
    ImageButton btCall;
    String sellerID;
    DatabaseReference databaseReference;

    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        send_email = findViewById(R.id.contact_button);
        memail = findViewById(R.id.pemail);
        msubject = findViewById(R.id.msubject);
        msg = findViewById(R.id.message_text);

        x=getIntent().getIntExtra("pos",0);
        getSupportActionBar().setTitle("Contact");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sellerID = getIntent().getStringExtra("sellerId");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child(sellerID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Userprofile userprofile = dataSnapshot.getValue(Userprofile.class);
                memail.setText(userprofile.getTxtEmail());
                msubject.setText("About Renting your house");
                mobile_no.setText(userprofile.getMobileNumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        mobile_no = findViewById(R.id.pnum1);
        btCall = findViewById(R.id.bt_call);
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mobile_no.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Number", Toast.LENGTH_SHORT).show();
                } else {
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    if (ActivityCompat.checkSelfPermission(Contact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private void sendMail() {
        String recipientList = memail.getText().toString();
        String[] recipients = recipientList.split(",");
        String subject = msubject.getText().toString();
        String message = msg.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email Client"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            startActivity(new Intent(this, Profile.class));

            return true;
        }
        if (id == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        if (id == android.R.id.home) {
           // Intent intent=new Intent(Contact.this,DetailPage.class);
            //intent.putExtra("pos",x);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //startActivity(intent);
            onBackPressed();

            finish();
        }
        return true;


    }
}