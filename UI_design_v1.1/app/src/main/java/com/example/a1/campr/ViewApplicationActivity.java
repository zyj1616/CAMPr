package com.example.a1.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.campr.fragment.PetsFragment;

public class ViewApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);
    }

    public void Approve(View view){
        Intent intent = new Intent(ViewApplicationActivity.this,ListerActivity.class);
        startActivity(intent);
    }
}
