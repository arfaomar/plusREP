package de.hda.plusrep.modules;

import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import de.hda.plusrep.Contacts;
import de.hda.plusrep.Forum;
import de.hda.plusrep.Profile;
import de.hda.plusrep.R;
import de.hda.plusrep.Settings;

public class Navbar {

    AppCompatActivity activeActivity;
    Intent activeIntent;

    public Navbar(AppCompatActivity activity, Intent intent) { this.activeActivity = activity; this.activeIntent = intent; }

    public void createNavbar() {

        BottomNavigationView navbar = activeActivity.findViewById(R.id.bottomNavbar);


        if (activeIntent.getIdentifier().equals("Profile")) navbar.setSelectedItemId(R.id.profileButton);
        if (activeIntent.getIdentifier().equals("Forum")) navbar.setSelectedItemId(R.id.forumButton);
        if (activeIntent.getIdentifier().equals("Contacts")) navbar.setSelectedItemId(R.id.contactsButton);
        if (activeIntent.getIdentifier().equals("Settings")) navbar.setSelectedItemId(R.id.settingsButton);


        navbar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.profileButton) {
                if (!activeIntent.getIdentifier().equals("Profile")) {

                    activeIntent.setClass(activeActivity.getApplicationContext(), Profile.class);
                    activeIntent.setIdentifier("Profile");
                    activeActivity.startActivity(activeIntent);
                    activeActivity.overridePendingTransition(0, 0);
                }
                return true;
            }

            if (item.getItemId() == R.id.forumButton) {
                if (!activeIntent.getIdentifier().equals("Forum")) {

                    activeIntent.setClass(activeActivity.getApplicationContext(), Forum.class);
                    activeIntent.setIdentifier("Forum");
                    activeActivity.startActivity(activeIntent);
                    activeActivity.overridePendingTransition(0, 0);
                }
                return true;
            }

            if (item.getItemId() == R.id.contactsButton) {
                if (!activeIntent.getIdentifier().equals("Contacts")) {

                    activeIntent.setClass(activeActivity.getApplicationContext(), Contacts.class);
                    activeIntent.setIdentifier("Contacts");
                    activeActivity.startActivity(activeIntent);
                    activeActivity.overridePendingTransition(0, 0);
                }
                return true;
            }

            if (item.getItemId() == R.id.settingsButton) {
                if (!activeIntent.getIdentifier().equals("Settings")) {

                    activeIntent.setClass(activeActivity.getApplicationContext(), Settings.class);
                    activeIntent.setIdentifier("Settings");
                    activeActivity.startActivity(activeIntent);
                    activeActivity.overridePendingTransition(0, 0);
                }
                return true;
            }

            return false;
        });
    }
}
