package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        BottomNavigationView navbar;
        navbar = findViewById(R.id.bottomNavbar);
        navbar.setSelectedItemId(R.id.contactsButton);

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