package com.example.ccsitacademicapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bca_fragment extends Fragment {
    Context context;

   // RecyclerView.Adapter adapter;

    RecyclerView recyclerView;
   // bca_adapter adapter;
    Button ft;
    List<DataClass> dataClassList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;


    public bca_fragment() {
        // Required empty public constructor
    }

   /* @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bca_fragment,
                container, false);
        recyclerView=view.findViewById(R.id.recycleview_bca);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(gridLayoutManager);*/

       /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(R.layout.pro)*/
        dataClassList = new ArrayList<>();
        bca_adapter adapter1 = new bca_adapter(context,dataClassList);
        recyclerView.setAdapter(adapter1);

/*
        databaseReference = FirebaseDatabase.getInstance().getReference("All Data").child("BCA").child("CT on monday all courses");


        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClassList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClassList.add(dataClass);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });*/
       /* recyclerView.setLayoutManager(new LinearLayoutManager(context));
        *//*FirebaseRecyclerOptions<model_bca> options =
                new FirebaseRecyclerOptions.Builder<model_bca>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("All Data").child("MCA1"), model_bca.class)
                        .build();*//*
       // adapter=new bca_adapter(options);
        recyclerView.setAdapter(adapter);*/

        ft = view.findViewById(R.id.float_but_bca);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


}