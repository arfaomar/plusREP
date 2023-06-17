package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;

import de.hda.plusrep.modules.Navbar;

public class Forum extends AppCompatActivity {

    Intent activeIntent;
    ArrayList<ForumPostModel> forumPostModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));



        activeIntent = getIntent();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();


        RecyclerView recyclerView = findViewById(R.id.forumRecView);
        forumPostModels.add(new ForumPostModel("Looking for friends :(","UserX","0"));
        //call setUpForumPostModels here
        ForumAdapter adapter = new ForumAdapter(this,forumPostModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}