package com.example.a1.campr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.campr.MyAdapter;
import com.example.a1.campr.Pets;
import com.example.a1.campr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.a1.campr.R.id.fragment_addnew;

public class PetsFragment extends Fragment {
    public static RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public static List<Pets> input = new ArrayList<>();
    public static HashMap<String, Pets> myPets = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pets,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getActivity().setContentView(R.layout.fragment_pets);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);

    }

}

