package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hda.plusrep.modules.DataHandler;
import de.hda.plusrep.modules.Navbar;

public class Forum extends AppCompatActivity implements ForumRecViewInterface{

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
        //forumPostModels.add(new ForumPostModel("Looking for friends :(","UserX"));
        //call setUpForumPostModels here
        setUpForumPostModels();
        ForumAdapter adapter = new ForumAdapter(this,forumPostModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton newPostButton = findViewById(R.id.newPostButton);
        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeIntent.setIdentifier("openPostCreation");
                activeIntent.setClass(newPostButton.getContext(), createPost.class);
                startActivity(activeIntent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        //Change Activity Here
        //Use intent.putExtra("name",contactModels.get(position).getSomething); to pass data to new Activity
    }

    private void setUpForumPostModels(){

        DataHandler dataHandler = DataHandler.getDataHandlerInstance();
        forumPostModels = dataHandler.getPostsFromDB();

    }

}