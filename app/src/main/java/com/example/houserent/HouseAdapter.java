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



public class HouseAdapter extends RecyclerView.Adapter <HouseAdapter.HouseViewHolder>{
    private Context mContext;
    private ArrayList<UploadDetails> mUploadDetailes;

    public HouseAdapter(Context context ,ArrayList<UploadDetails> uploadDetails){
        mContext=context;
        mUploadDetailes=uploadDetails;
    }
    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row, parent,false);
        return new HouseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        UploadDetails uploadCurrent = mUploadDetailes.get(position);
        holder.textViewTitle.setText(uploadCurrent.getTitle());
        holder.textViewPrice.setText(uploadCurrent.getPrice());
        holder.textViewAddress.setText(uploadCurrent.getAddress());
        String ar=uploadCurrent.getArea();
        holder.textViewArea.setText(ar);

        Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);

        holder.cardView.setOnClickListener(v -> {//card view onclick listener
            Intent intent=new Intent(mContext, DetailPage.class);
            //Bundle bundle=new Bundle();
            //bundle.putParcelableArrayList("detail",);
            intent.putExtra("detail",mUploadDetailes);
            intent.putExtra("pos",position);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //System.out.println("I am in house adaptor");
            mContext.startActivity(intent);


        });

    }

    @Override
    public int getItemCount() {
        return mUploadDetailes.size();
    }

    public class HouseViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle,textViewPrice,textViewArea,textViewAddress;
        public ImageView imageView;
        public CardView cardView;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_title);
            textViewArea=itemView.findViewById(R.id.text_area);
            textViewAddress=itemView.findViewById(R.id.text_address);
            textViewPrice=itemView.findViewById(R.id.text_price);
            imageView=itemView.findViewById(R.id.image_view_upload);
            cardView=itemView.findViewById(R.id.card_view);
        }
    }
}
