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

import com.example.a1.campr.AdopterActivity;
import com.example.a1.campr.ListerActivity;
import com.example.a1.campr.LoginActivity;
import com.example.a1.campr.R;
import com.example.a1.campr.RegistrationActivity;
import com.example.a1.campr.ViewApplicationActivity;

public class ApplicationFragment extends Fragment {
    private Button viewmore2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_application,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewmore2 = getActivity().findViewById(R.id.button2);
        viewmore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewApplicationActivity.class);
                startActivity(intent);
            }
        });
    }
}




