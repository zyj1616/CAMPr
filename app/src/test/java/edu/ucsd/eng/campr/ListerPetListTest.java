package edu.ucsd.eng.campr;

import android.support.v7.app.AppCompatActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListerPetListTest extends AppCompatActivity {

    @Test
    public void addPetToDatabaseTest() {
        int size = ListerPetList.myPets.size();
        Pets test = new Pets("test","test","test","testId",null);
        ListerPetList.addPetToDatabase(test);
        assertEquals(ListerPetList.myPets.size(),size + 1);
        assertTrue(ListerPetList.myPets.containsKey("testId"));
        //todo test if data upload successfully
    }
}