package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import de.hda.plusrep.modules.DataHandler;

public class FriendRequests extends AppCompatActivity {

    ArrayList<FriendRequestsModel> friendRequestsModels;
    DataHandler dataHandler = DataHandler.getDataHandlerInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);
        RecyclerView recyclerView = findViewById(R.id.friendReqRecView);
        setup();
        FriendRequestsAdapter adapter = new FriendRequestsAdapter(this, friendRequestsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

}