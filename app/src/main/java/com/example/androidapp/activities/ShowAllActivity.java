
package com.example.androidapp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.adapters.ShowAllAdapter;
import com.example.androidapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;
    Toolbar toolbar;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String type = getIntent().getStringExtra("type");
        firestore = FirebaseFirestore.getInstance();


        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, showAllModelList);
        recyclerView.setAdapter(showAllAdapter);


        if (type == null || type.isEmpty()) {


            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    });

        }


        if (type != null && type.equalsIgnoreCase("Kids")) {

            firestore.collection("ShowAll").whereEqualTo("type", "Kids")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    });
        }

            if (type != null && type.equalsIgnoreCase("Watch")) {

                firestore.collection("ShowAll").whereEqualTo("type", "Watch")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        });


            }

            if (type != null && type.equalsIgnoreCase("Woman")) {

                firestore.collection("ShowAll").whereEqualTo("type", "Woman")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        });


            }

            if (type != null && type.equalsIgnoreCase("Shoes")) {

                firestore.collection("ShowAll").whereEqualTo("type", "Shoes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        });
            }
            if (type != null && type.equalsIgnoreCase("Men")) {

                firestore.collection("ShowAll").whereEqualTo("type", "men")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        });

            }
            if (type != null && type.equalsIgnoreCase("Camera")) {

                firestore.collection("ShowAll").whereEqualTo("type", "Camera")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = documentSnapshot.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        });
            }
        }
    }
