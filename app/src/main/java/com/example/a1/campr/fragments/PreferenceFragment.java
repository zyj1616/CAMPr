package com.example.a1.campr.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.a1.campr.AdopterActivity;
import com.example.a1.campr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PreferenceFragment extends Fragment {
    private Spinner spinnerSpecies;
    private Spinner spinnerAge;
    private Spinner spinnerColor;
    private Spinner spinnerSize;
    private Spinner spinnerAdoptionFee;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preference,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();

        spinnerSpecies = (Spinner) getActivity().findViewById(R.id.spinner_species);
        ArrayAdapter<CharSequence> adapter_species = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.species,android.R.layout.simple_spinner_item);
        adapter_species.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSpecies.setAdapter(adapter_species);

        spinnerAge = (Spinner) getActivity().findViewById(R.id.spinner_age);
        ArrayAdapter<CharSequence> adapter_age = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.age,android.R.layout.simple_spinner_item);
        adapter_age.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter_age);

        spinnerColor = (Spinner) getActivity().findViewById(R.id.spinner_color);
        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.color,android.R.layout.simple_spinner_item);
        adapter_color.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapter_color);

        spinnerSize = (Spinner) getActivity().findViewById(R.id.spinner_size);
        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.size,android.R.layout.simple_spinner_item);
        adapter_size.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter_size);

        spinnerAdoptionFee = (Spinner) getActivity().findViewById(R.id.spinner_adoption_fee);
        ArrayAdapter<CharSequence> adapter_adoption_fee = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.adoption_fee,android.R.layout.simple_spinner_item);
        adapter_adoption_fee.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAdoptionFee.setAdapter(adapter_adoption_fee);

        Button applyButton = getActivity().findViewById(R.id.button);
        applyButton.setOnClickListener((v) -> {
            String species = spinnerSpecies.getSelectedItem().toString();
            String age = spinnerAge.getSelectedItem().toString();
            String color = spinnerColor.getSelectedItem().toString();
            String size = spinnerSize.getSelectedItem().toString();
            String adoptionFee = spinnerAdoptionFee.getSelectedItem().toString();

            ((AdopterActivity)getActivity()).changeFilters(species, age, color, size, adoptionFee);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SwipeCardsFragment()).commit();
        });
    }
}
