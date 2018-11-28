package edu.ucsd.eng.campr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListerPetList extends AppCompatActivity {

    private TextView mTextMessage;
    public static RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public static List<Pets> petsArrayListForRecyclerView = new ArrayList<>();
    public static HashMap<String, Pets> myPets = new HashMap<>();

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //intent = new Intent(getApplicationContext(), ListerPetList.class);
                    //startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    //intent = new Intent(getApplicationContext(), SwitchActivity.class);
                    //startActivity(intent);
                    return true;
                case R.id.navigation_switch:
                    intent = new Intent(getApplicationContext(), SwitchActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_pet_list);


        mTextMessage = (TextView) findViewById(R.id.message);
        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ViewPetsAdapter(petsArrayListForRecyclerView);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    protected void newPet(View view) {
        startActivity(new Intent(this, AddPetActivity.class));
    }

}
