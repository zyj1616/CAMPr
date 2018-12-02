package com.example.a1.campr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.campr.models.Adopter;
import com.example.a1.campr.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewApplicantActivity extends AppCompatActivity {
    private TextView name, gender, info;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference adopterRef;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pets);

        ImageView edit = findViewById(R.id.edit_pet);
        edit.setVisibility(View.GONE);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();
        String adopterId = getIntent().getExtras().getString("adopter_id");
        adopterRef =mDatabaseRef.child("adopters").orderByKey().equalTo(adopterId).getRef();
        adopterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Adopter adopter = snapshot.getValue(Adopter.class);
                name = findViewById(R.id.textView);
                name.setInputType(InputType.TYPE_NULL);
                name.setText(adopter.getFirstname());
                gender = findViewById(R.id.textView2);
                gender.setInputType(InputType.TYPE_NULL);
                info = findViewById(R.id.textView3);
                info.setInputType(InputType.TYPE_NULL);
                info.setText(adopter.getLastname());

                Button accept = findViewById(R.id.button);
                accept.setText("ACCEPT");
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                Button decline = findViewById(R.id.button_right);
                decline.setText("Decline");
                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // do nothing
            }
        });

    }
}
