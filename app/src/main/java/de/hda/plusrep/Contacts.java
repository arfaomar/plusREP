package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import de.hda.plusrep.modules.DataHandler;
import de.hda.plusrep.modules.Navbar;

public class Contacts extends AppCompatActivity implements ContactsRecViewInterface{

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
        //contactModels.add(new ContactModel("test","69","https://avatars.steamstatic.com/e1eb3504352089689deb2a873abb462cadba13f6_full.jpg"));
        // call setUpContacts from ContactModel once we have data and can create the class
        setUpContacts();
        ContactsAdapter adapter = new ContactsAdapter(this, contactModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    // ---------------------- End Of Recycler View Code

        activeIntent = getIntent();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }

    @Override
    public void onItemClick(int position) {
        //Change Activity Here
        //Use intent.putExtra("name",contactModels.get(position).getSomething); to pass data to new Activity
    }

    private void setUpContacts() {
        Long steamID = 76561198106115768L; //TODO: MAKE PUBLIC STATIC steamID variable
        DataHandler dataHandler = DataHandler.getDataHandlerInstance();
        UserData currentUser = dataHandler.getUserFromDB(steamID);
        ArrayList<ContactModel> contacts = new ArrayList<>();

        if (currentUser == null) {
            Log.e("NULL","USER IS NULL");
        }
        for (Long friendID : currentUser.getFriendsList()) {
            UserData friend = dataHandler.getUserFromDB(friendID);

            if (friend == null) {
                Log.e("NULL","FRIEND IS NULL");
            }

            if (currentUser.checkFriendship(friend)) {
                Double rpScore = friend.calculateRepScore();
                Integer rps = rpScore.intValue();
                contacts.add(new ContactModel(friend.getPersonaName(), String.valueOf(rps), friend.getAvatarURL(), friend.getSteamID()));
            }

        }
        contactModels = contacts;

    }


}