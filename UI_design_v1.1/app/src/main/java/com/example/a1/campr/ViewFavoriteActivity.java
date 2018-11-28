package com.example.a1.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.campr.fragment.PetsFragment;

public class ViewFavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorite);
    }

    public void applyPet(View view){
        Intent intent = new Intent(ViewFavoriteActivity.this,AdopterActivity.class);
        startActivity(intent);
    }
}
