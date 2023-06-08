package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navbar;
        navbar = findViewById(R.id.bottomNavbar);
        navbar.setSelectedItemId(R.id.settingsButton);

        navbar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.profileButton) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0,0);
                //finish();
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

                return true;
            }

            return false;
        });
    }
}