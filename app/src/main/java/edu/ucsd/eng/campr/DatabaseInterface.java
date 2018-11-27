package edu.ucsd.eng.campr;

import java.util.HashMap;

public final class DatabaseInterface {

    private static DatabaseInterface INSTANCE;

    private static HashMap<String, Pets> myPets;

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
}