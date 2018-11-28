package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
    }

    public void setUserRole(View view) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            Intent goToLoginActivity = new Intent(this, FirebaseUiActivity.class);
            startActivity(goToLoginActivity);

            // Update database
            User user = new User(firebaseUser);
            DatabaseInterface.getInstance().addUser(user);
        }

        Switch s = findViewById(R.id.userTypeSwitch);
        if (s.isChecked()) {
            startActivity(new Intent(this, ListerPetList.class));
        }
        else {
            // startActivity( new Intent((this, TODO:Put Class Name Here)));
        }
    }
}
