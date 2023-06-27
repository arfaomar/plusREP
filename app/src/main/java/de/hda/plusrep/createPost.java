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
import android.widget.TextView;

import de.hda.plusrep.modules.DataHandler;

public class createPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String chosenGame;
    private String[] courses = { "CS:GO", "Call of Duty",
            "GTA V", "Kindergarten 2",
            "Destiny 2", "Big Thinkers Kindergarten", "Children of Morta" };
    DataHandler dataHandler = DataHandler.getDataHandlerInstance();
    UserData user = dataHandler.getUserFromDB(Long.parseLong(Profile.steamID));
    Intent activeIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));
        activeIntent = getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.chooseTagSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                R.layout.spinner_text,
                courses);
        // Specify the layout to use when the list of choices appears
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(ad);


        TextView userNameText = findViewById(R.id.createPostUserNameTextView);
        userNameText.setText(user.getPersonaName());

        Button createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText postTitle = findViewById(R.id.createPostTitleText);
                EditText postText = findViewById(R.id.createPostText);

                if (user == null) {
                    Log.e("createPost", "User Data is Null");
                }

                EditText editText = findViewById(R.id.createPostTitleText);
                String title = editText.getText().toString();

                String userName = user.getPersonaName();
                editText = findViewById(R.id.createPostText);
                String content = editText.getText().toString();
                editText.setText(user.getPersonaName());
                Integer gameTagID = dataHandler.getGameTagIDbyNamefromDB(chosenGame);

                dataHandler.addPostToDB(new ForumPostModel(Long.parseLong(Profile.steamID), title, userName, content, chosenGame, gameTagID));

                activeIntent.setIdentifier("createPost");
                activeIntent.setClass(createPostButton.getContext(), Forum.class);
                startActivity(activeIntent);
            }
        });

        Button cancelPostButton = findViewById(R.id.cancelPostButton);
        cancelPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeIntent.setIdentifier("Forum");
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
