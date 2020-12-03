package com.example.houserent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Upload extends AppCompatActivity {

    private static final int PICK_IMAGE_REQ=1;

    private Button mButtonChooseimage,mButtonUpload;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private TextInputEditText mTextTitle,mTextPrice,mTextArea,mTextAddress,mTextFloorNo,mTextBedroom,mTextBathroom,mTextBalcony,mTextFurnishing;
    private TextInputEditText mTextMaintenance,mTextTotalFloor,mTextCarParking,mTextFacing,mTextListed,mTextCity,mTextBachelorsAllow;


    private Uri mImageUri;
    private ArrayList<Uri> ImageUri;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser currentFirebaseUser ;
    private StorageTask mUploadtask;
    String sellerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        mButtonChooseimage=findViewById(R.id.button_choose_image);
        mImageView=findViewById(R.id.upload_image_view);
        mProgressBar=findViewById(R.id.progress_bar);
        mButtonUpload=findViewById(R.id.button_upload);
        mTextTitle=findViewById(R.id.text_title);
        mTextAddress=findViewById(R.id.text_address);
        mTextArea=findViewById(R.id.text_area);
        mTextPrice=findViewById(R.id.text_price);
        mTextFloorNo=findViewById(R.id.text_floor_no);
        mTextBedroom=findViewById(R.id.text_bedroom);
        mTextBathroom=findViewById(R.id.text_bathroom);
        mTextBalcony=findViewById(R.id.text_balcony);
        mTextFurnishing=findViewById(R.id.text_furnishing);
        mTextBachelorsAllow=findViewById(R.id.text_bachelors_allow);
        mTextMaintenance=findViewById(R.id.text_maintenance);
        mTextTotalFloor=findViewById(R.id.text_total_floor);
        mTextCarParking=findViewById(R.id.text_car_parking);
        mTextFacing=findViewById(R.id.text_facing);
        mTextListed=findViewById(R.id.text_listed);
        mTextCity=findViewById(R.id.text_city);
        ImageUri=new ArrayList<>();




        getSupportActionBar().setTitle("Upload House");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mStorageReference= FirebaseStorage.getInstance().getReference("houses");
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("houses");
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        sellerID = currentFirebaseUser.getUid();

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadtask!=null && mUploadtask.isInProgress()){
                    Toast.makeText(Upload.this, "Upload in Progress...", Toast.LENGTH_SHORT).show();
                }
                else{
                uploadFile();
                }
            }
        });


        mButtonChooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
      //  intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,PICK_IMAGE_REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQ && resultCode == RESULT_OK && data != null && data.getData()!= null){
            mImageUri = data.getData();
            if(mImageUri!=null) {
                ImageUri.add(mImageUri);
                Picasso.get().load(mImageUri).into(mImageView);
            }
            //mImageView.setImageURI(mImageUri);
        }
    }


    private String getFileExtension(Uri uri){
        ContentResolver cR =getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(mImageUri!=null) {

            final StorageReference fileReference = mStorageReference.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            ArrayList<StorageReference> fileref=new ArrayList<>();
            for (Uri uri:ImageUri) {
                fileref.add(mStorageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri)));

            }
            mUploadtask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },5000);
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String id= String.valueOf(System.currentTimeMillis());
                                    String getuid=FirebaseAuth.getInstance().getUid();
                                    UploadDetails uploadDetails=new UploadDetails(mTextTitle.getText().toString().trim(),
                                            mTextAddress.getText().toString().trim(), mTextArea.getText().toString().trim(),
                                            mTextPrice.getText().toString().trim(),uri.toString(),mTextFloorNo.getText().toString().trim(),
                                            mTextBedroom.getText().toString().trim(),mTextBathroom.getText().toString().trim(),
                                            mTextBalcony.getText().toString().trim(),mTextFurnishing.getText().toString().trim(),
                                            mTextBachelorsAllow.getText().toString().trim(),mTextMaintenance.getText().toString().trim(),
                                            mTextTotalFloor.getText().toString().trim(),mTextCarParking.getText().toString().trim(),
                                            mTextFacing.getText().toString().trim(), mTextListed.getText().toString().trim(),
                                            mTextCity.getText().toString().trim(),id,"Yes",sellerID);

                                    mDatabaseReference.child(id).setValue(uploadDetails);
                                    mDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(getuid).child("house");
                                    mDatabaseReference.child(id).setValue(uploadDetails);
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Upload.this, "Upload Successful", Toast.LENGTH_LONG).show();

                                }
                            });
                            }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = ( 100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress( (int) progress);
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
    }
    }
    @Override
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
        onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        return true;
    }

}
