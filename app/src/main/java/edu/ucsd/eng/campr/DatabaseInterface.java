package edu.ucsd.eng.campr;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.google.gson.Gson;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public final class DatabaseInterface {

    private static DatabaseInterface INSTANCE;
    private static HashMap<String, Pets> myPets;
    private static HashMap<String, User> users;

    private DatabaseInterface() {
        myPets = new HashMap<>();
    }

    public static DatabaseInterface getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseInterface();
        }

        return INSTANCE;
    }

    public String addPet(Pets pet) {
        myPets.put(pet.getPetId(), pet);
        return pet.getPetId();
    }
    // getters and setters

    public Pets getPetByID(String key) {
        return myPets.get(key);
    }

    public boolean deletePet(String key) {
        if (myPets.remove(key) == null) {
            return false;
        }
        else return true;
    }

    public String addUser(User user) {
        users.put(user.getUserID(), user);
        return user.getUserID();
    }

    public User getUserById(String id) {
        return  users.get(id);
    }
    // Todo: Save data when app closes
}