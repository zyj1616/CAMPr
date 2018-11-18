package edu.ucsd.eng.campr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet);

        Pets pet = getIntent().getParcelableExtra("parcel_data");
        TextView textView = findViewById(R.id.textView);
        textView.setText(pet.getName());
        textView = findViewById(R.id.textView2);
        textView.setText(pet.getGender());
        textView = findViewById(R.id.textView3);
        textView.setText(pet.getInfo());
        ImageView imageView = findViewById(R.id.imageView);
        pet = ListerPetList.myPets.get(pet.getPetId());
        imageView.setImageBitmap(pet.getPetPic());
    }
}
