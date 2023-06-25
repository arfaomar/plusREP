package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;


import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hda.plusrep.modules.DataHandler;
import de.hda.plusrep.modules.Navbar;

public class Forum extends AppCompatActivity implements ForumRecViewInterface{

    Intent activeIntent;
    ArrayList<ForumPostModel> forumPostModels = new ArrayList<>();

    private Chip csGoChip, codChip, kindergarten2Chip,
            gtaVChip, destiny2Chip, bigThinkersChip, childrenOfMortaChip;

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

        csGoChip = findViewById(R.id.csChip);
        codChip = findViewById(R.id.codChip);
        kindergarten2Chip = findViewById(R.id.kind2Chip);
        gtaVChip = findViewById(R.id.gtaChip);
        destiny2Chip = findViewById(R.id.destinyChip);
        bigThinkersChip = findViewById(R.id.bigThinkerChip);
        childrenOfMortaChip = findViewById(R.id.childofMortaChip);

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    String selectedChip = buttonView.getText().toString();
                    DataHandler dataHandler = DataHandler.getDataHandlerInstance();
                    Log.d("test", selectedChip);
                    forumPostModels = dataHandler.getForumPostbyGameName(selectedChip);
                    adapter.notifyDataSetChanged();
                } else {
                    setUpForumPostModels();
                    Log.d("test","else");
                    adapter.notifyDataSetChanged();
                }
            }
        };


        csGoChip.setOnCheckedChangeListener(checkedChangeListener);
        codChip.setOnCheckedChangeListener(checkedChangeListener);
        kindergarten2Chip.setOnCheckedChangeListener(checkedChangeListener);
        gtaVChip.setOnCheckedChangeListener(checkedChangeListener);
        destiny2Chip.setOnCheckedChangeListener(checkedChangeListener);
        bigThinkersChip.setOnCheckedChangeListener(checkedChangeListener);
        childrenOfMortaChip.setOnCheckedChangeListener(checkedChangeListener);

    }

    @Override
    public void onItemClick(int position) {
        //Change Activity Here
        //Use intent.putExtra("name",contactModels.get(position).getSomething); to pass data to new Activity
        activeIntent.putExtra("postid", forumPostModels.get(position).getPostID().toString());
        activeIntent.setIdentifier("openPost");
        activeIntent.setClass(this, ForumPost.class);
        startActivity(activeIntent);
    }

    private void setUpForumPostModels(){

        DataHandler dataHandler = DataHandler.getDataHandlerInstance();
        forumPostModels = dataHandler.getPostsFromDB();

    }

}