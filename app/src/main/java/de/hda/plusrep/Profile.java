package de.hda.plusrep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView navbar;
        navbar = findViewById(R.id.bottomNavbar);
        navbar.setSelectedItemId(R.id.profileButton);

        navbar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.profileButton) {
                return true;
            }

            if (item.getItemId() == R.id.forumButton) {
                startActivity(new Intent(getApplicationContext(), Forum.class));
                overridePendingTransition(0, 0);
                //finish();
                return true;
            }

            if (item.getItemId() == R.id.contactsButton) {
                startActivity(new Intent(getApplicationContext(), Contacts.class));
                overridePendingTransition(0, 0);
                //finish();
                return true;
            }

            if (item.getItemId() == R.id.settingsButton) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
                overridePendingTransition(0,0);
                //finish();
                return true;
            }

            return false;
        });

    }





}