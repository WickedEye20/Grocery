package com.example.grocery.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.adapters.PopularAdapters;
import com.example.grocery.adapters.ViewAllAdapter;
import com.example.grocery.models.PopularModel;
import com.example.grocery.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    PopularAdapters popularAdapters;

    List<PopularModel> popularModelList;
    List<ViewAllModel> viewAllModelList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
//        popularModelList = new ArrayList<>();

        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
//        popularAdapters = new PopularAdapters(this, popularModelList);

        recyclerView.setAdapter(viewAllAdapter);


        // getting fruits
        if (type != null && type.equalsIgnoreCase("fruits")) {
            firestore.collection("AllProducts").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

//        firestore.collection("PopularProducts")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                PopularModel popularModel = document.toObject(PopularModel.class);
//                                popularModelList.add(popularModel);
//                                popularAdapters.notifyDataSetChanged();
//
////                                progressBar.setVisibility(View.GONE);
////                                scrollView.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            Toast.makeText(ViewAllActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


        // getting vegetables
//            if (type != null && type.equalsIgnoreCase("vegetable")) {
//                firestore.collection("ALlProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
//                            ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
//                            viewAllModelList.add(viewAllModel);
//                            viewAllAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//            }

        }
    }