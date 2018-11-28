package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewUserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);
        DatabaseInterface db = DatabaseInterface.getInstance();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        User u = db.getUserById(id);

        TextView name = findViewById(R.id.displayName);
        name.append(u.getDisplayName());
        TextView email = findViewById(R.id.email);
        email.append(u.getEmailAddress());
        TextView phone = findViewById(R.id.phoneNumber);
        phone.append(u.getPhoneNumber());
    }
}