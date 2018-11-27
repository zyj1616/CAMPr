package edu.ucsd.eng.campr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

public class AddPetActivity extends AppCompatActivity {
    private static final int RESULT_REQUEST =1;
    private Bitmap pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
    }

    protected void listPet(View view) { //save
        EditText temp = findViewById(R.id.editTextPetName);
        String name = temp.getText().toString();
        DatabaseInterface db = DatabaseInterface.getInstance();

        String gender;
        RadioGroup group = findViewById(R.id.radioGroupGender);
        int radioBtnSelected = group.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(radioBtnSelected);
        if (selectedGender == findViewById(R.id.radioMale)) {
            gender = "male";
        }
        else { gender = "female"; }

        temp = findViewById(R.id.editTextPetDescription);
        String info = temp.getText().toString();

        ListerPetList.input.add(new Pets(name, gender, info, name+gender+info, pic)); // Add to list for recyclerView
        //ListerPetList.myPets.put(name+gender+info, new Pets(name, gender, info, name+gender+info, pic)); // Add to hashmap
        db.addPet(new Pets(name, gender, info, name+gender+info, pic)); // Add to database
        ListerPetList.recyclerView.getAdapter().notifyDataSetChanged(); //Update view
        finish();
    }

    protected void addPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //From addPicture
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RESULT_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        pic = bitmap;
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}
