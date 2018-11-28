package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPetActivity extends AppCompatActivity {
    Pets pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet);
        DatabaseInterface db = DatabaseInterface.getInstance();

        pet = getIntent().getParcelableExtra("parcel_data");
        TextView textView = findViewById(R.id.textView);
        textView.setText(pet.getName());
        textView = findViewById(R.id.textView2);
        textView.setText(pet.getGender());
        textView = findViewById(R.id.textView3);
        textView.setText(pet.getInfo());
        ImageView imageView = findViewById(R.id.imageView);
        //pet = ListerPetList.myPets.get(pet.getPetId());
        pet = db.getPetByID(pet.getPetId());
        imageView.setImageBitmap(pet.getPetPic());
    }

    protected void deletePet(View view) {
        DatabaseInterface db = DatabaseInterface.getInstance();
        if (db.deletePet(pet.getPetId())) {
            ListerPetList.input.remove(pet);
            ListerPetList.recyclerView.getAdapter().notifyDataSetChanged();
            startActivity(new Intent(this, ListerPetList.class));;
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Could not delete from the database", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void viewPetApplications(View view) {
        //startActivity(new Intent(this, AddPetActivity.class));
    }
}
