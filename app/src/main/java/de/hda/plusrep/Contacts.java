package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;

import de.hda.plusrep.modules.Navbar;

public class Contacts extends AppCompatActivity {

    Intent activeIntent;

    ArrayList<ContactModel> contactModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));



    // ---------------------- Recycler View Code
        RecyclerView recyclerView = findViewById(R.id.contactRecView);

        contactModels.add(new ContactModel("test","69","https://avatars.steamstatic.com/e1eb3504352089689deb2a873abb462cadba13f6_full.jpg"));
        // call setUpContacts from ContactModel once we have data and can create the class

        ContactsAdapter adapter = new ContactsAdapter(this,contactModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    // ---------------------- End Of Recycler View Code



        activeIntent = getIntent();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }
}