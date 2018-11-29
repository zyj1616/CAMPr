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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a1.campr.Pet;
import com.example.a1.campr.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddNewFragment extends Fragment {
    private static final int RESULT_REQUEST =1;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private Uri imageUri;
    private ByteArrayOutputStream baos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addnew,container,false);
//        View view = inflater.inflate(this,)
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference();

        FragmentActivity activity = getActivity();
        activity.findViewById(R.id.name).requestFocus();

        DatabaseReference petsRef = mDatabase.getReference("pets");

        ImageView image = activity.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_REQUEST);
            }
        });

        Button addPetButton = activity.findViewById(R.id.add_pet_button);
        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                EditText name = activity.findViewById(R.id.name);
                String nameStr = name.getText().toString();
                EditText gender = activity.findViewById(R.id.gender);
                String genderStr = gender.getText().toString();
                EditText info = activity.findViewById(R.id.description);
                String infoStr = info.getText().toString();

                // Get the new pet's key in the database for storing the image

                DatabaseReference imageUrlRef = mDatabaseRef.child("pets").child(mFirebaseUser.getUid());
                String key = imageUrlRef.push().getKey();

                // Upload the pet image to Firebase Cloud Storage and retrieve the link

                StorageReference imageRef = mStorage
                        .getReference(mFirebaseUser.getUid())
                        .child(key)
                        .child(imageUri.getLastPathSegment());

                putImageInStorage(imageRef, imageUri, imageUrlRef, nameStr, genderStr, infoStr, key);
            }
        });
    }

    private void putImageInStorage(final StorageReference storageReference, final Uri uri, final DatabaseReference imageUrlRef, final String name, final String gender, final String info, final String key) {
        storageReference.putBytes(baos.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadPhotoUrl) {
                        imageUrlRef.child(key).setValue(new Pet(name, gender, info, key, downloadPhotoUrl.toString()))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new AddNewFragment()).commit();
                                    }
                                });
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RESULT_REQUEST:
                    imageUri = data.getData();
                    try {
                        baos = new ByteArrayOutputStream();
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

                        // Crop the bitmap

                        Bitmap croppedBitmap;
                        if (imageBitmap.getWidth() >= imageBitmap.getHeight()){

                            croppedBitmap = Bitmap.createBitmap(
                                    imageBitmap,
                                    imageBitmap.getWidth()/2 - imageBitmap.getHeight()/2,
                                    0,
                                    imageBitmap.getHeight(),
                                    imageBitmap.getHeight()
                            );

                        }else{

                            croppedBitmap = Bitmap.createBitmap(
                                    imageBitmap,
                                    0,
                                    imageBitmap.getHeight()/2 - imageBitmap.getWidth()/2,
                                    imageBitmap.getWidth(),
                                    imageBitmap.getWidth()
                            );
                        }

                        croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        ImageView imageView = getActivity().findViewById(R.id.image);
                        imageView.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}

