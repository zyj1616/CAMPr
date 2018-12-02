package com.example.a1.campr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1.campr.AdopterActivity;
import com.example.a1.campr.R;

public class PreferenceFragment extends Fragment {
    Spinner spinner_gender;
    Spinner spinner_species;
    Spinner spinner_age;
    Spinner spinner_color;
    Spinner spinner_size;
    Spinner spinner_adoption_fee;
    ArrayAdapter<CharSequence> adapter_species;
    ArrayAdapter<CharSequence> adapter_gender;
    ArrayAdapter<CharSequence> adapter_age;
    ArrayAdapter<CharSequence> adapter_color;
    ArrayAdapter<CharSequence> adapter_size;
    ArrayAdapter<CharSequence> adapter_adoption_fee;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preference,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        spinner_species = (Spinner) getActivity().findViewById(R.id.spinner_species);
        adapter_species = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.species,R.layout.my_spinner);
        adapter_species.setDropDownViewResource(R.layout.my_spinner);
        spinner_species.setAdapter(adapter_species);

        spinner_gender = (Spinner) getActivity().findViewById(R.id.spinner_gender);
        adapter_gender = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.genders,R.layout.my_spinner);
        adapter_gender.setDropDownViewResource(R.layout.my_spinner);
        spinner_gender.setAdapter(adapter_gender);

        spinner_age = (Spinner) getActivity().findViewById(R.id.spinner_age);
        adapter_age = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.age,R.layout.my_spinner);
        adapter_age.setDropDownViewResource(R.layout.my_spinner);
        spinner_age.setAdapter(adapter_age);

        spinner_color = (Spinner) getActivity().findViewById(R.id.spinner_color);
        adapter_color = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.color,R.layout.my_spinner);
        adapter_color.setDropDownViewResource(R.layout.my_spinner);
        spinner_color.setAdapter(adapter_color);

        spinner_size = (Spinner) getActivity().findViewById(R.id.spinner_size);
        adapter_size = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.size,R.layout.my_spinner);
        adapter_size.setDropDownViewResource(R.layout.my_spinner);
        spinner_size.setAdapter(adapter_size);

        spinner_adoption_fee = (Spinner) getActivity().findViewById(R.id.spinner_adoption_fee);
        adapter_adoption_fee = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.adoption_fee,R.layout.my_spinner);
        adapter_adoption_fee.setDropDownViewResource(R.layout.my_spinner);
        spinner_adoption_fee.setAdapter(adapter_adoption_fee);


    }
}
