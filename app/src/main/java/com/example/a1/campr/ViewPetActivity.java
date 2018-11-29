package com.example.a1.campr;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.campr.fragment.PetsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewPetActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference petRef;

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();

        String pet_id = getIntent().getStringExtra("pet_id");

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

                Glide.with(picImageView.getContext())
                        .load(pet.getPicUrl())
                        .into(picImageView);
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
