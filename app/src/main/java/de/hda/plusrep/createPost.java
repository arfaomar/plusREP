package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import de.hda.plusrep.modules.DataHandler;

public class createPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String chosenGame;
    private String[] courses = { "CS:GO", "Call of Duty",
            "GTA V", "Kindergarten 2",
            "Destiny 2", "Big Thinkers Kindergarten", "Children of Morta" };
    Intent activeIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        Spinner spinner = (Spinner) findViewById(R.id.chooseTagSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        // Specify the layout to use when the list of choices appears
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(ad);

        activeIntent = getIntent();



        Button createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText postTitle = findViewById(R.id.createPostTitleText);
                EditText postText = findViewById(R.id.createPostText);

                DataHandler dataHandler = DataHandler.getDataHandlerInstance();

                UserData userData = dataHandler.getUserFromDB(Profile.steamId);

                if (userData == null) {
                    Log.e("createPost", "User Data is Null");
                }

                EditText editText = findViewById(R.id.createPostTitleText);
                String title = editText.getText().toString();

                String userName = userData.getPersonaName();

                editText = findViewById(R.id.createPostText);
                String content = editText.getText().toString();

                Integer gameTagID = dataHandler.getGameTagIDbyNamefromDB(chosenGame);

                dataHandler.addPostToDB(new ForumPostModel(Profile.steamId, title, userName, content, chosenGame, gameTagID));

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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        chosenGame = parent.getItemAtPosition(pos).toString();
        Log.i("choseGame", chosenGame);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}