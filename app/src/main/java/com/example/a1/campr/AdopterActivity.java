package com.example.a1.campr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1.campr.fragment.FavoriteFragment;
import com.example.a1.campr.fragment.PreferenceFragment;
import com.example.a1.campr.fragment.ProfileFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.huxq17.swipecardsview.BaseCardAdapter;
import com.huxq17.swipecardsview.SwipeCardsView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdopterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private ArrayList<Pet> petList;
    private SwipeCardsView swipeCardsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if(savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new ProfileFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_profile);
//        }

        petList = new ArrayList<Pet>();
        Pet a = new Pet("haha", "haha", "haha", "haha", "https://firebasestorage.googleapis.com/v0/b/campr-e847b.appspot.com/o/imtecH3JobOdGcltiV8onYabqw03%2F-LSSkfoI346_PG8Gy_oQ%2F345?alt=media&token=7c0fafb8-7b3a-4883-9b27-255f9ccb7706", "haha");
        Pet b = new Pet("haha", "haha", "haha", "haha", "https://firebasestorage.googleapis.com/v0/b/campr-e847b.appspot.com/o/J6PqPGpcvMMFq8HHc7pAhX7ub9F2%2F-LSSkxvCohzIvuWlqeZf%2F342?alt=media&token=a6252e2f-39dd-41fb-bc95-390475946354", "haha");
        petList.add(a);
        petList.add(b);

        // New method
        swipeCardsView = findViewById(R.id.swipe_cards_view);
        swipeCardsView.retainLastCard(false);
        swipeCardsView.enableSwipe(true);

        DatabaseReference petsRef = mDatabaseRef.child("pets");

        petsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<Pet> list = new ArrayList<>();
                for (DataSnapshot petSnapshot: snapshot.getChildren()) {
                    list.add(petSnapshot.getValue(Pet.class));
                }

                PetAdapter petAdapter = new PetAdapter(list, AdopterActivity.this);
                swipeCardsView.setAdapter(petAdapter);

//                final PetAdapter arrayAdapter = new PetAdapter(AdopterActivity.this, petList);
//                SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
//                flingContainer.setAdapter(arrayAdapter);
//                flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
//                    @Override
//                    public void removeFirstObjectInAdapter() {
//                        // this is the simplest way to delete an object from the Adapter (/AdapterView)
//                        Log.d("LIST", "removed object!");
//                        petList.remove(0);
//                        arrayAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onLeftCardExit(Object dataObject) {
//                        //Do something on the left!
//                        //You also have access to the original object.
//                        //If you want to use it just cast it (String) dataObject
//                        Toast.makeText(AdopterActivity.this, "Left", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onRightCardExit(Object dataObject) {
//                        Toast.makeText(AdopterActivity.this, "Right", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                    }
//
//                    @Override
//                    public void onScroll(float scrollProgressPercent) {
//
//                    }
//                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // do nothing
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        View view = findViewById(R.id.swipe_cards_view);
        switch (item.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                view.setVisibility(View.GONE);
                break;
            case R.id.nav_preference:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PreferenceFragment()).commit();
                view.setVisibility(View.GONE);
                break;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteFragment()).commit();
                view.setVisibility(View.GONE);
                break;
            case R.id.nav_signout:
                mFirebaseAuth.signOut();
                Intent intent = new Intent(AdopterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private class PetAdapter extends ArrayAdapter<Pet> {
//        private Context mContext;
//        private List<Pet> mlist;
//
//        public PetAdapter(@NonNull Context context, ArrayList<Pet> list) {
//            super(context, 0 , list);
//            mContext = context;
//            mlist = list;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            View listItem = convertView;
//            if (listItem == null)
//                listItem = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
//
//            Pet pet = mlist.get(position);
//
//            ImageView imageView = listItem.findViewById(R.id.image);
//            Glide.with(imageView.getContext())
//                    .load(pet.getPicUrl())
//                    .into(imageView);
//
//            TextView nameTextView = listItem.findViewById(R.id.name);
//            nameTextView.setText(pet.getName());
//
//            TextView genderTextView = listItem.findViewById(R.id.gender);
//            genderTextView.setText(pet.getGender());
//
//            TextView descriptionTextView = listItem.findViewById(R.id.description);
//            descriptionTextView.setText(pet.getInfo());
//
//            return listItem;
//        }
//    }

    public class PetAdapter extends BaseCardAdapter {
        private List<Pet> petList;
        private Context context;

        public PetAdapter(List<Pet> petList, Context context) {
            this.petList = petList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return petList.size();
        }

        @Override
        public int getCardLayoutId() {
            return R.layout.item;
        }

        @Override
        public void onBindData(int position, View cardview) {
            if (petList == null || petList.size() == 0) {
                return;
            }

            Pet pet = petList.get(position);

            ImageView imageView = cardview.findViewById(R.id.image);
            Glide.with(imageView.getContext())
                    .load(pet.getPicUrl())
                    .into(imageView);

            TextView nameTextView = cardview.findViewById(R.id.name);
            nameTextView.setText(pet.getName());

            TextView genderTextView = cardview.findViewById(R.id.gender);
            genderTextView.setText(pet.getGender());

            TextView descriptionTextView = cardview.findViewById(R.id.description);
            descriptionTextView.setText(pet.getInfo());
        }

        @Override
        public int getVisibleCardCount() {
            return super.getVisibleCardCount();
        }
    }
}
