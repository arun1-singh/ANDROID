package com.example.androidapp.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.androidapp.R;
import com.example.androidapp.activities.ShowAllActivity;
import com.example.androidapp.adapters.CategoryAdapter;
import com.example.androidapp.adapters.NewProductsAdapter;
import com.example.androidapp.adapters.PopularProductsAdapter;
import com.example.androidapp.models.CategoryModel;
import com.example.androidapp.models.NewProductsModel;
import com.example.androidapp.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    TextView catShowAll,popularShowAll,newProductShowAll;

LinearLayout linearLayout;
    ProgressDialog progressDialog;

    RecyclerView catRecyclerview,newProductRecyclerView,popularRecyclerview;
     //Category RecyclerView
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //New Product Recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;

    FirebaseFirestore db;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getActivity());
        db = FirebaseFirestore.getInstance();

        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);
        catRecyclerview = root.findViewById(R.id.rec_category);
        newProductRecyclerView = root.findViewById(R.id.new_product_rec);
        popularRecyclerview = root.findViewById(R.id.popular_rec);
        catShowAll = root.findViewById(R.id.category_see_all);
        popularShowAll = root.findViewById(R.id.popular_see_all);
        newProductShowAll = root.findViewById(R.id.newProducts_see_all);




        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        //image slider
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List< SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoes items", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume",ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF",ScaleTypes.CENTER_INSIDE));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome To My Ecommerce App");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

       //Category
        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                CategoryModel categoryModel = documentSnapshot.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                        } else {

                        }
                    }
                });

        //New Products
        newProductRecyclerView.setLayoutManager((new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false)));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerView.setAdapter(newProductsAdapter);


        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                NewProductsModel newProductsModel  = documentSnapshot.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        popularRecyclerview.setLayoutManager((new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false)));
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                PopularProductsModel popularProductsModel  = documentSnapshot.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });



                        return root;
                    }

                }