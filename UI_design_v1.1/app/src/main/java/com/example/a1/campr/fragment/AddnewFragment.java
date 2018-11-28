package com.example.a1.campr.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a1.campr.ListerActivity;
import com.example.a1.campr.Pets;
import com.example.a1.campr.R;

import java.io.IOException;

public class AddnewFragment extends Fragment {
    private static final int RESULT_REQUEST =1;
    private Bitmap pic;
    private Button listpet;
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addnew,container,false);
//        View view = inflater.inflate(this,)
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.name).requestFocus();

        image = getActivity().findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_REQUEST);
            }
        });

        listpet = getActivity().findViewById(R.id.listpet);
        listpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText temp = getActivity().findViewById(R.id.name);
                String name = temp.getText().toString();
                temp = getActivity().findViewById(R.id.editText3);
                String gender = temp.getText().toString();
                temp = getActivity().findViewById(R.id.editText);
                String info = temp.getText().toString();
                PetsFragment.input.add(new Pets(name, gender, info, name+gender+info, pic));
                PetsFragment.myPets.put(name+gender+info, new Pets(name, gender, info, name+gender+info, pic));

                Intent intent = new Intent(getActivity(),ListerActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new PetsFragment(),null).addToBackStack(null).commit();


            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RESULT_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        pic = bitmap;
                        ImageView imageView = getActivity().findViewById(R.id.imageView);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}

