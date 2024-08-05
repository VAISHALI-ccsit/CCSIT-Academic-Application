package com.example.ccsitacademicapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Principal_Launch_Adapter extends RecyclerView.Adapter<Principal_Launch_Adapter.ViewHolder> {

    Principal_Launch_Fragment context;
   // ArrayList<ContactModel> arrayList;
    ArrayList<DataClass> dataClasses;
    /* Principal_Launch_Adapter(Principal_Launch_Fragment context, ArrayList<ContactModel> arrayList){
       this.context=context;
       this.arrayList=arrayList;
    }*/
    Principal_Launch_Adapter(Principal_Launch_Fragment context, ArrayList<DataClass> dataClasses){
        this.context=context;
        this.dataClasses=dataClasses;
    }


    @NonNull
    @Override
    public Principal_Launch_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_launch_principal, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Principal_Launch_Adapter.ViewHolder holder, int position) {
         /*holder.img.setImageResource(arrayList.get(position).img);
         holder.edi.setText(arrayList.get(position).notification);*/
        //holder.img.setImageResource(dataClasses.get(position).getImg());
        holder.img.setImageURI(Uri.parse(dataClasses.get(position).getImageURL()));
        holder.edi.setText(dataClasses.get(position).getCaption());

    }

    @Override
    public int getItemCount() {
        return dataClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText edi;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edi=itemView.findViewById(R.id.edit_notification);
            img=itemView.findViewById(R.id.img_add);

        }
    }

}
