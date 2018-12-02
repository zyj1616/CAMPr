package com.example.a1.campr;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.campr.fragments.ApplyMsgDialog;
import com.example.a1.campr.models.Application;
import com.example.a1.campr.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference petRef;
    private DatabaseReference applicationRef;
    private boolean hasApplied = false;
    private String ListerId;
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favourite_pet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();

        final String pet_id = getIntent().getStringExtra("pet_id");

        petRef = mDatabaseRef.child("pets").child(pet_id);
        petRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Pet pet = snapshot.getValue(Pet.class);
                TextView nameTextView = findViewById(R.id.name);
                nameTextView.setText(pet.getName());
                TextView genderTextView = findViewById(R.id.gender);
                genderTextView.setText(pet.getGender());
                TextView descriptionTextView = findViewById(R.id.description);
                descriptionTextView.setText(pet.getInfo());
                ImageView picImageView = findViewById(R.id.pic);
                ListerId = pet.getListerId();
                Glide.with(picImageView.getContext())
                        .load(pet.getPicUrl())
                        .into(picImageView);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // do nothing
            }
        });
        applicationRef = mDatabaseRef.child("adopters").child(mFirebaseUser.getUid()).child("chosenPets").child(pet_id);
        applicationRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.getValue() == null){
                    hasApplied = false;
                }
                else{
                    hasApplied = snapshot.getValue(Boolean.class);
                }
                //todo withdraw application
                if(hasApplied){
                    Button remove = findViewById(R.id.button);
                    remove.setText("Withdraw");
                    Button goBack = findViewById(R.id.button_right);
                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDatabaseRef.child("adopters").child(mFirebaseUser.getUid()).child("chosenPets").child(pet_id).setValue(false);
                            finish();
                        }
                    });
                    goBack.setText("Go back");
                    goBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
                else{
                    Button Apply = findViewById(R.id.button);
                    Apply.setText("Apply");
                    Apply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDatabaseRef.child("adopters").child(mFirebaseUser.getUid()).child("chosenPets").child(pet_id).setValue(true);
                            Application application = new Application(ListerId,mFirebaseUser.getUid(),pet_id,false," ",false);
                            mDatabaseRef.child("applications").push().setValue(application);
                            finish();
                        }
                    });
                    Button goBack = findViewById(R.id.button_right);
                    goBack.setText("Go back");
                    goBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // do nothing
            }
        });
    }

    public void deletePet(View view) {
        petRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
                finish();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // do nothing
            }
        });

        finish();
    }
}
