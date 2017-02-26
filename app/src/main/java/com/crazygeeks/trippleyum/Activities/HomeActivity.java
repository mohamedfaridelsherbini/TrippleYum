package com.crazygeeks.trippleyum.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crazygeeks.trippleyum.Fragments.HomeFragment;
import com.crazygeeks.trippleyum.Models.MainListModel;
import com.crazygeeks.trippleyum.R;

public class HomeActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainFragContainer , homeFragment).commit();

    }

    @Override
    public void onListFragmentInteraction(MainListModel item) {
        
    }
}
