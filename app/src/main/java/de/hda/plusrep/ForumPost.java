package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import de.hda.plusrep.modules.DataHandler;

public class ForumPost extends AppCompatActivity {

    Intent activeIntent;
    String steamid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        activeIntent = getIntent();

        Integer postID = Integer.parseInt(activeIntent.getStringExtra("postid"));

        steamid = activeIntent.getStringExtra("steamid");

        DataHandler dataHandler = DataHandler.getDataHandlerInstance();
        ForumPostModel currPost = dataHandler.getForumPostById(postID);

        if (currPost == null) {
            Log.e("ForumPost", "CURRENT POST DATA IS NULL");
        }

        TextView title = findViewById(R.id.bigForumPostTitle);
        TextView content = findViewById(R.id.bigForumPostContent);
        TextView username = findViewById(R.id.bigForumPostUsername);
        Chip tagChip = findViewById(R.id.tagChip);
        ImageButton exitButton = findViewById(R.id.exitButton);
        CardView userNameTextHolder = findViewById(R.id.userNameTextViewHolder);

        title.setText(currPost.getTitle());
        content.setText(currPost.getContent());
        username.setText(currPost.getUserName());
        tagChip.setText(currPost.getGameTag());

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeIntent.setIdentifier("Forum");
                activeIntent.setClass(getApplicationContext(), Forum.class);
                startActivity(activeIntent);
            }
        });

        userNameTextHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //TODO
                activeIntent.setIdentifier("otherProfile");
                activeIntent.putExtra("Profile.steamID", steamid);
                activeIntent.putExtra("Profile." + steamid + ".loaded", "FALSE");
                activeIntent.setClass(getApplicationContext(), otherProfile.class);
                startActivity(activeIntent);
            }
        });

    }
}