package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FriendRequests extends AppCompatActivity {

    ArrayList<FriendRequestsModel> friendRequestsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);
        RecyclerView recyclerView = findViewById(R.id.friendReqRecView);
        //setup
        FriendRequestsAdapter adapter = new FriendRequestsAdapter(this, friendRequestsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}