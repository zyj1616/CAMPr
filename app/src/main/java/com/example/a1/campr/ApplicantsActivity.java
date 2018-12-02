package com.example.a1.campr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.campr.LoginActivity;
import com.example.a1.campr.R;
import com.example.a1.campr.fragments.FavoriteFragment;
import com.example.a1.campr.models.Adopter;
import com.example.a1.campr.models.Application;
import com.example.a1.campr.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
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

import java.util.ArrayList;

public class ApplicantsActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter_applicants;
    public RecyclerView.LayoutManager layoutManager;
    public LinearLayoutManager mLinearLayoutManager;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference AdopterRef;
    private FirebaseRecyclerAdapter<Adopter, ApplicantsActivity.AdopterViewHolder> mFirebaseAdapter;
    private String petId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pets);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();

        petId = getIntent().getExtras().getString("pet_id");

        SnapshotParser<Adopter> parser = new SnapshotParser<Adopter>() {
            @NonNull
            @Override
            public Adopter parseSnapshot(@NonNull DataSnapshot dataSnapshot) {
                Adopter adopter = dataSnapshot.getValue(Adopter.class);
                if (adopter != null) {
                    adopter.setId(dataSnapshot.getKey());
                }
                return adopter;
            }
        };
        Query petQuery = mDatabaseRef.child("adopter/chosenPets").orderByChild(petId).equalTo(true);
        //todo 通过 petId找所有的application并加载到recycle review里面
        FirebaseRecyclerOptions<Adopter> options =
                new FirebaseRecyclerOptions.Builder<Adopter>()
                        .setQuery(petQuery, parser)
                        .build();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Adopter, AdopterViewHolder>(options) {
            @Override
            public AdopterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View v = inflater.inflate(R.layout.row_layout, parent, false);
                return new AdopterViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(final ApplicantsActivity.AdopterViewHolder viewHolder, int position, Adopter adopter) {
                viewHolder.headerTextView.setText(adopter.getFirstname());
                viewHolder.footerTextView.setText(adopter.getLastname());
                viewHolder.idTextView.setText(adopter.getId());
                Glide.with(viewHolder.picImageView.getContext())
                        .load(adopter.getPicUrl())
                        .into(viewHolder.picImageView);
            }
        };
    }
    //show adopter
    public class AdopterViewHolder extends RecyclerView.ViewHolder {
        private ImageView picImageView;
        private TextView headerTextView;
        private TextView footerTextView;
        private TextView idTextView;
        public View layout;

        private AdopterViewHolder(View v) {
            super(v);
            layout = v;
            picImageView = v.findViewById(R.id.icon);
            headerTextView = v.findViewById(R.id.first_line);
            footerTextView = v.findViewById(R.id.second_line);
            idTextView = v.findViewById(R.id.pet_id);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            new AddNewFragment()).commit();
                    String key = idTextView.getText().toString();
                    //show profile of clicked adopter
                    Intent intent = new Intent(v.getContext(), ViewApplicantActivity.class);
                    intent.putExtra("pet_id", key);
                    intent.putExtra("adopter_id",petId);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
