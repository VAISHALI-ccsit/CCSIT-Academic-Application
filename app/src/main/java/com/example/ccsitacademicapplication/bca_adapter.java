package com.example.ccsitacademicapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class bca_adapter extends  RecyclerView.Adapter<bca_adapter.MyViewHolder> {

    Context context;
    List<DataClass> dataList;

    public bca_adapter(Context context,  List<DataClass> dataList){
        this.context=context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_bca, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.recImage);
        holder.recnotificaion.setText(dataList.get(position).getCaption());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, bca_fragment.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getImageURL());
                intent.putExtra("Notification", dataList.get(holder.getAdapterPosition()).getCaption());
                //intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getImageURL());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recnotificaion;

        CardView recCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.img_add_bca);
            recnotificaion = itemView.findViewById(R.id.edit_notification_bca);
            recCard = itemView.findViewById(R.id.cardview_recyclerview);
        }
    }
}

//public class bca_adapter extends FirebaseRecyclerAdapter<model_bca, bca_adapter.myviewholder> {

   /* public bca_adapter(@NonNull FirebaseRecyclerOptions<model_bca> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model_bca model) {

        holder.notification_bca.setText(model_bca.getNotification_bca());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_bca, parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
       // ImageView img;
        TextView notification_bca;
        public myviewholder(View itemView){
            super(itemView);
           // img = (ImageView)itemView.findViewById(R.id.img_add_bca);
            notification_bca= itemView.findViewById(R.id.edit_notification_bca);
        }
    }

}
*/

