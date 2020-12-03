package com.example.houserent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DetailPage extends AppCompatActivity {
    TextView price_fix,price_house,bhk_fix,bhk_house,area_fix,area_house,address_fix,address_house;
    TextView city_fix,city_house,additional_details,floor_fix,floor_house,balcony_fix,balcony_house;
    TextView bedroom_fix,bedroom_house,bathroom_fix,bathroom_house,furnish_fix,furnish_house;
    TextView bachelor_fix,bachelor_house,maintenance_fix,maintenance_house,total_floor_fix,listed_house;
    TextView total_floor_house,car_park_fix,car_park_house,facing_fix,facing_house,listed_fix;

    Button btn_contact;
    ImageView img_detail_page1;
    ScrollView scrollView;
    HorizontalScrollView horizontalScrollView;
    View view,view1,view3,view4,view5,view6,view9,view10,view11,view12,view13,view14,view15;
    String sellerID;
    UploadDetails upDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        btn_contact=findViewById(R.id.contact_button);


        img_detail_page1=findViewById(R.id.imageView_detail_page1);


        scrollView=findViewById(R.id.scrollView_detail_page);
        horizontalScrollView=findViewById(R.id.horizontal_scrollView_imageView);

        price_fix=findViewById(R.id.price_fix);
        price_house=findViewById(R.id.price_house);
        bhk_fix=findViewById(R.id.BHK_fix);
        bhk_house=findViewById(R.id.BHK_house);
        area_fix=findViewById(R.id.area_fix);
        area_house=findViewById(R.id.area_house);
        address_fix=findViewById(R.id.address_fix);
        address_house=findViewById(R.id.address_house);
        city_fix=findViewById(R.id.City_fix);
        city_house=findViewById(R.id.City_house);
        additional_details=findViewById(R.id.Additional_details);
        floor_fix=findViewById(R.id.Floor_fix);
        floor_house=findViewById(R.id.Floor_house);
        balcony_fix=findViewById(R.id.Balcony_fix);
        balcony_house=findViewById(R.id.Balcony_house);
        bedroom_fix=findViewById(R.id.Bedroom_fix);
        bedroom_house=findViewById(R.id.Bedroom_house);
        bathroom_fix=findViewById(R.id.Bathroom_fix);
        bathroom_house=findViewById(R.id.Bathroom_house);
        maintenance_fix=findViewById(R.id.Maintenance_fix);
        maintenance_house=findViewById(R.id.Maintenance_house);
        furnish_fix=findViewById(R.id.Furnish_fix);
        furnish_house=findViewById(R.id.Furnish_house);
        bachelor_fix=findViewById(R.id.Bachelors_fix);
        bachelor_house=findViewById(R.id.Bachelors_house);
        total_floor_fix=findViewById(R.id.Total_floor_fix);
        total_floor_house=findViewById(R.id.Total_floor_house);
        car_park_fix=findViewById(R.id.Car_parking_fix);
        car_park_house=findViewById(R.id.Car_parking_house);
        facing_fix=findViewById(R.id.Facing_fix);
        facing_house=findViewById(R.id.Facing_house);
        listed_fix=findViewById(R.id.Listed_fix);
        listed_house=findViewById(R.id.Listed_house);
        upDetail=new UploadDetails();
       // System.out.println("Detail page 1");
        ArrayList<UploadDetails> list;
        list = (ArrayList<UploadDetails>) getIntent().getSerializableExtra("detail");
        //   System.out.println("Detail page 2");
        assert list != null;
        upDetail=list.get(getIntent().getIntExtra("pos",0));
      //  System.out.println("Detail page 3");
        int a;
        a=getIntent().getIntExtra("pos",0);
        view=findViewById(R.id.view);
        view1=findViewById(R.id.view1);
        view3=findViewById(R.id.view3);
        view4=findViewById(R.id.view4);
        view5=findViewById(R.id.view5);
        view6=findViewById(R.id.view6);
        view9=findViewById(R.id.view9);
        view10=findViewById(R.id.view10);
        view11=findViewById(R.id.view11);
        view12=findViewById(R.id.view12);
        view13=findViewById(R.id.view13);
        view14=findViewById(R.id.view14);
        view15=findViewById(R.id.view15);
        getSupportActionBar().setTitle("House Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        price_house.setText("Rs "+upDetail.getPrice());
        bhk_house.setText(upDetail.getBedroom());
        area_house.setText(upDetail.getArea()+" Sqf");
        address_house.setText(upDetail.getAddress());
        city_house.setText(upDetail.getCity());
        floor_house.setText(upDetail.getFloorNo());
        balcony_house.setText(upDetail.getBalcony());
        bedroom_house.setText(upDetail.getBedroom());
        bathroom_house.setText(upDetail.getBathroom());
        furnish_house.setText(upDetail.getFurnishing());
        bachelor_house.setText(upDetail.getBachelorsAllow());
        maintenance_house.setText("Rs "+upDetail.getMaitenance());
        total_floor_house.setText(upDetail.getTotalFloor());
        car_park_house.setText(upDetail.getCarParking());
        facing_house.setText(upDetail.getFacing());
        listed_house.setText(upDetail.getListed());
        //Picasso.get().load(upDetail.getmImageUrl()).fit().into(img_detail_page1);
        Glide.with(getApplicationContext()).load(upDetail.getmImageUrl()).into(img_detail_page1);
        sellerID = upDetail.getSellerID();
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPage.this,Contact.class);
                intent.putExtra("sellerId",sellerID);
                intent.putExtra("pos",a);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
            Intent intent=new Intent(this,Houselist.class);
            startActivity(intent);
        }
        return true;
    }
}
