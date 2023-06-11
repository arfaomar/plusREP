package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    ArrayList<ContactModel> contactModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

    // ---------------------- Recycler View Code
        RecyclerView recyclerView = findViewById(R.id.contactRecView);

        contactModels.add(new ContactModel("test","69",R.drawable.ic_contacts));
        // call setUpContacts from ContactModel once we have data and can create the class

        ContactsAdapter adapter = new ContactsAdapter(this,contactModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    // ---------------------- End Of Recycler View Code

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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