package com.example.ghureyfiri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set up ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set default fragment when the app launches
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setContentView(R.menu.nav_menu);
        // Handle navigation item selection
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new HomeFragment())
                        .commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new SettingsFragment())
                        .commit();
                break;

            case R.id.nav_destinations:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new DestinationsFragment())
                        .commit();
                break;

            case R.id.nav_offers:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new OffersFragment())
                        .commit();
                break;

            case R.id.nav_logout:
                // Handle logout action
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                // Add actual logout functionality here if needed
                break;

            default:
                break;
        }

        // Close the navigation drawer after item selection
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Close the drawer if open, else perform default back press
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
