package com.example.a1.campr.fragment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.a1.campr.MainActivity;
import com.example.a1.campr.Pet;
import com.example.a1.campr.Pets;
import com.example.a1.campr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseTooManyRequestsException;
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
    private Bitmap imageBitmap;

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

//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageBytes = baos.toByteArray();
//                UploadTask uploadTask = imageRef.putBytes(imageBytes);
//                uploadTask.addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                        // ...
//                    }
//                });

//                PetsFragment.input.add(new Pets(nameStr, genderStr, infoStr, nameStr + genderStr + infoStr, pic));
//                PetsFragment.myPets.put(nameStr + genderStr + infoStr, new Pets(nameStr, genderStr, infoStr, nameStr + genderStr + infoStr, pic));
//                PetsFragment.recyclerView.getAdapter().notifyDataSetChanged();
//
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new PetsFragment()).commit();

//                getActivity().finish();
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, new );

            }
        });
    }

    private void putImageInStorage(final StorageReference storageReference, final Uri uri, final DatabaseReference imageUrlRef, final String name, final String gender, final String info, final String key) {
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    imageUrlRef.child(key).setValue(new Pet(name, gender, info, key, storageReference.getDownloadUrl().toString()));
                    Log.w("TAG", "Image upload task was not successful.",
                            task.getException());
                } else {
                    Log.w("TAG", "Image upload task was not successful.",
                            task.getException());
                }
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
                        imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                        ImageView imageView = getActivity().findViewById(R.id.image);
                        imageView.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}

