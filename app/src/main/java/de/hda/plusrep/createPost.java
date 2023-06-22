package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createPost extends AppCompatActivity {

    Intent activeIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        activeIntent = getIntent();

        Button createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText postTitle = findViewById(R.id.createPostTitleText);
                EditText postText = findViewById(R.id.createPostText);
                activeIntent.putExtra("Post Title", postTitle.getText().toString());
                activeIntent.putExtra("Post Text", postText.getText().toString());

                activeIntent.setIdentifier("createPost");
                activeIntent.setClass(createPostButton.getContext(), Forum.class);
                startActivity(activeIntent);
            }
        });

        Button cancelPostButton = findViewById(R.id.cancelPostButton);
        cancelPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeIntent.setIdentifier("createPost");
                activeIntent.setClass(cancelPostButton.getContext(), Forum.class);
                startActivity(activeIntent);
            }
        });
    }
}