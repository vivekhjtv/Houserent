package com.example.houserent;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView btn_rent,btn_sell;
    NavigationView navigationView;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        btn_rent=findViewById(R.id.imageButtonRent);
        btn_sell=findViewById(R.id.imageButtonSale);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        btn_rent.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Houselist.class)));
        btn_sell.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Upload.class)));

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.profile){
            //profile
            startActivity(new Intent(this,Profile.class));

            return true;
        }
        if(id==R.id.sign_out){
            //signout
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }
        return true;
        }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home) {

            startActivity(new Intent(MainActivity.this,MainActivity.class));
        }
        if (menuItem.getItemId() == R.id.nav_rent) {
            startActivity(new Intent(MainActivity.this,Houselist.class));
        }
        if (menuItem.getItemId() == R.id.nav_profile) {
            startActivity(new Intent(MainActivity.this,Profile.class));
            this.finish();
        }
        if (menuItem.getItemId() == R.id.nav_my_uploads) {
            System.out.println("MyUploads");
            startActivity(new Intent(MainActivity.this,MyUploads.class));
            this.finish();
        }

        if (menuItem.getItemId() == R.id.nav_sell) {
            startActivity(new Intent(MainActivity.this,Upload.class));
            this.finish();
        }
        if (menuItem.getItemId() == R.id.nav_about_us) {
            finish();
            startActivity(new Intent(MainActivity.this,AboutUs.class));
        }
        if (menuItem.getItemId() == R.id.nav_help) {
            finish();
            startActivity(new Intent(MainActivity.this, Help.class));
        }

        if(menuItem.getItemId()== R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }

        //drawerLayout.closeDrawers();
        return true;
    }
}
