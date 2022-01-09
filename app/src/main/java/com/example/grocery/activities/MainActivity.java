package com.example.grocery.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.grocery.Cart;
import com.example.grocery.Home;
import com.example.grocery.Profile;
import com.example.grocery.R;
import com.example.grocery.Search;
import com.example.grocery.SessionsManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textView = findViewById(R.id.uid);
        SessionsManager sessionsManager = new SessionsManager(this);
//        HashMap<String, String> userDetails = sessionsManager.getUserDetailFromSession();

//        String username = userDetails.get(SessionsManager.KEY_USERNAME);

//        textView.setText(username);

//        side Navigation DRawer
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Home");
        loadFragment(new Home());

//
//        drawerLayout = findViewById(R.id.my_drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.Home:
                        getSupportActionBar().setTitle("Home");
                        fragment = new Home();
                        loadFragment(fragment);
//                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Cart:
                        getSupportActionBar().setTitle("Cart");
                        fragment = new Cart();
                        loadFragment(fragment);
//                        Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Search:
                        getSupportActionBar().setTitle("Search");
                        fragment = new Search();
                        loadFragment(fragment);
//                        Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Profile:
                        getSupportActionBar().setTitle("Profile");
                        fragment = new Profile();
                        loadFragment(fragment);
//                        Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Logout:
                        getSupportActionBar().setTitle("Logout");

                        sessionsManager.logoutUserFromSession(); //logout
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
//                        Toast.makeText(MainActivity.this, "Successfully Logged out", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        getSupportActionBar().setTitle("Home");
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
