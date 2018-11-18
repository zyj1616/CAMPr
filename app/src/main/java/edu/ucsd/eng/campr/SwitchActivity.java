package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
    }

    public void setUserRole(View view) {
        Switch s = findViewById(R.id.userTypeSwitch);
        if (s.isChecked()) {
            startActivity(new Intent(this, ListerPetList.class));
        }
        else {
            // startActivity( new Intent((this, TODO:Put Class Name Here)));
        }
    }
}
