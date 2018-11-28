package com.example.a1.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.campr.fragment.PetsFragment;

import static com.example.a1.campr.fragment.PetsFragment.input;

public class ViewPetsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pets);

        Pets pet = getIntent().getParcelableExtra("parcel_data");
        TextView textView = findViewById(R.id.textView);
        textView.setText(pet.getName());
        textView = findViewById(R.id.textView2);
        textView.setText(pet.getGender());
        textView = findViewById(R.id.textView3);
        textView.setText(pet.getInfo());
        ImageView imageView = findViewById(R.id.imageView);
        pet = PetsFragment.myPets.get(pet.getPetId());
        imageView.setImageBitmap(pet.getPetPic());

    }

    public void deletePet(View view) {
        Pets pet = getIntent().getParcelableExtra("parcel_data");
        input.remove(pet);
        PetsFragment.recyclerView.getAdapter().notifyDataSetChanged();
        finish();
    }


}
