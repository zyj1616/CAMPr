package com.example.a1.campr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a1.campr.AdopterActivity;
import com.example.a1.campr.LoginActivity;
import com.example.a1.campr.R;
import com.example.a1.campr.models.Adopter;
import com.example.a1.campr.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ApplicationFragment extends Fragment {
    public RecyclerView mRecyclerView;
    public LinearLayoutManager mLinearLayoutManager;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseRecyclerAdapter<Pet, FavoriteFragment.PetViewHolder> mFirebaseAdapter;
    public static Pet pet;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_application,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FragmentActivity activity = getActivity();

        Button backButton = getActivity().findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdopterActivity.class);
                startActivity(intent);
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();

    }
/*
    *//**
     * 根据petId
     * @return 所有的申请人
     *//*
    public ArrayList<Adopter> retrieveApplicants() {
        ArrayList<Adopter> applicants = new ArrayList<>();
        for(String key : pet.applicantId.keySet()) {
            for(Adopter u : LoginActivity.myData.dataUsers) {
                if(u.getId().equals(key)) {
                    applicants.add(u);
                }
            }
        }
        return applicants;
    }*/
}

