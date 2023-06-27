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

public class FriendRequests extends AppCompatActivity {

    Intent activeIntent;
    ArrayList<FriendRequestsModel> friendRequestsModels;
    DataHandler dataHandler = DataHandler.getDataHandlerInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);
        activeIntent = getIntent();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));
        RecyclerView recyclerView = findViewById(R.id.friendReqRecView);
        setup();
        FriendRequestsAdapter adapter = new FriendRequestsAdapter(this, friendRequestsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }

    private void setup() {
        ArrayList<FriendRequestsModel> friendRequests = new ArrayList<>();
        ArrayList<Long> friends = dataHandler.getFriendRequest(Long.parseLong(Profile.steamID));
        UserData userData;
        for(Long friend: friends) {
            userData = dataHandler.getUserFromDB(friend);
            if(userData == null) {

                Log.e("NULL","UserData IS NULL");

            }
            friendRequests.add(new FriendRequestsModel(userData.getSteamID(), userData.getPersonaName(), userData.getAvatarURL()));
        }
        this.friendRequestsModels = friendRequests;
    }

    public Intent getActiveIntent() {
        return activeIntent;
    }
}