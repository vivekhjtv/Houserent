package com.example.houserent;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Help extends AppCompatActivity {

    TextView need_help,htrh_qn,htrh_ans,htsh_qn,htsh_ans,con_us,con_phone,con_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        need_help=findViewById(R.id.text_help);
        htrh_qn=findViewById(R.id.text_hrh_qn);
        htrh_ans=findViewById(R.id.text_hrh_ans);
        htsh_qn=findViewById(R.id.text_hsh_qn);
        htsh_ans=findViewById(R.id.text_hsh_ans);
        con_us=findViewById(R.id.text_help_contact);
        con_phone=findViewById(R.id.contact_us_phone);
        con_email=findViewById(R.id.contact_us_email);


        con_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(v);
            }
        });
    }
    public void call(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("8200180732"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        startActivity(callIntent);
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
            onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        return true;

    }
}
