package com.example.houserent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AboutUs extends AppCompatActivity {
    TextView title,title1,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_about_us);
        title=findViewById(R.id.house_title);
        title1=findViewById(R.id.house_title1);
        des=findViewById(R.id.house_des);
        getSupportActionBar().setTitle("About us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
