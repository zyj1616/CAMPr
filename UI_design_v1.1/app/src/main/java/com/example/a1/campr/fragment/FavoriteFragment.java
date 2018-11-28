package com.example.a1.campr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1.campr.AdopterActivity;
import com.example.a1.campr.R;
import com.example.a1.campr.ViewApplicationActivity;
import com.example.a1.campr.ViewFavoriteActivity;

public class FavoriteFragment extends Fragment {
    private Button viewmore1;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewmore1 = getActivity().findViewById(R.id.button1);
        viewmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewFavoriteActivity.class);
                startActivity(intent);
            }
        });
    }

}

