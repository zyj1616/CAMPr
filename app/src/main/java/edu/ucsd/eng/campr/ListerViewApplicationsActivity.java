package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListerViewApplicationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_view_applications);
    }

    public void approveApplication(View view) {
        startActivity(new Intent(this, ViewUserProfileActivity.class));
    }
}
