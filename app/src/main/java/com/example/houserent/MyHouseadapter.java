package com.example.houserent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyHouseadapter extends RecyclerView.Adapter<MyHouseadapter.HouseViewHolder1> {

    public Context ctx;
    public ArrayList<UploadDetails> uploadDetails;

    public MyHouseadapter(Context context, ArrayList<UploadDetails> uploadDetails) {
        ctx = context;
        this.uploadDetails = uploadDetails;
    }

    public HouseViewHolder1 onCreateViewHolder(ViewGroup parent, int viewtype) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.row, parent, false);
        return new MyHouseadapter.HouseViewHolder1(v);
    }

    public void onBindViewHolder(HouseViewHolder1 holder, int position) {
        UploadDetails uploadCurrent = uploadDetails.get(position);
        holder.textViewTitle.setText(uploadCurrent.getTitle());
        holder.textViewPrice.setText( uploadCurrent.getPrice());
        holder.textViewAddress.setText(uploadCurrent.getAddress());
        String ar = uploadCurrent.getArea() ;
        holder.textViewArea.setText(ar);
        System.out.println(ar);
        Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,MyHouse.class);
                intent.putExtra("detail",uploadDetails);
                intent.putExtra("pos",position);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });


    }

    public int getItemCount() {
        return uploadDetails.size();
    }

    public class HouseViewHolder1 extends RecyclerView.ViewHolder {
        public TextView textViewTitle, textViewPrice, textViewArea, textViewAddress;
        public ImageView imageView;
        public CardView cardView;

        public HouseViewHolder1(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_title);
            textViewArea = itemView.findViewById(R.id.text_area);
            textViewAddress = itemView.findViewById(R.id.text_address);
            textViewPrice = itemView.findViewById(R.id.text_price);
            imageView = itemView.findViewById(R.id.image_view_upload);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
