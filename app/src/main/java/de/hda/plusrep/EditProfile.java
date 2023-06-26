package de.hda.plusrep;

import static android.widget.TextView.BufferType.EDITABLE;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;

import de.hda.plusrep.modules.DataHandler;

public class EditProfile extends AppCompatActivity {

    DataHandler dataHandler = DataHandler.getDataHandlerInstance();
    Intent activeIntent;

    EditText editText;
    EditText editDiscordText;
    Button editDiscordButton;
    UserData currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        activeIntent = getIntent();
        ImageView avatar = findViewById(R.id.editUserAvatar);
        TextView username = findViewById(R.id.editUsername);

        currentUser = dataHandler.getUserFromDB(Long.parseLong(Profile.steamID));

        Glide.with(this).load(currentUser.getAvatarURL())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(avatar);
        username.setText(currentUser.getPersonaName());

        editText = findViewById(R.id.editBiographyText);
        editDiscordText = findViewById(R.id.editDiscordButtonText);
        editDiscordButton = findViewById(R.id.editDiscordButton);
        ImageButton exitBtn = findViewById(R.id.editProfileExitButton);

        readUserData();
        ImageButton saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.editBiographyText);
                editDiscordText = findViewById(R.id.editDiscordButtonText);
                updateUserData();
                activeIntent.setIdentifier("editProfile");
                activeIntent.setClass(saveButton.getContext(), Profile.class);
                startActivity(activeIntent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeIntent.setIdentifier("editProfile");
                activeIntent.setClass(exitBtn.getContext(), Profile.class);
                startActivity(activeIntent);
            }
        });

    }

    private void readUserData(){

       String currentBio = currentUser.getBiography();
       editText.setText(currentBio, EDITABLE);
       String currentDiscord = String.valueOf(currentUser.getDiscordID());
       editDiscordText.setText(currentDiscord, EDITABLE);

    }

    private void updateUserData(){
        dataHandler.updateUserInDB(new UserData(currentUser.getSteamID(), currentUser.getPersonaName(), currentUser.getProfileURL(), currentUser.getAvatarURL(), editText.getText().toString(), currentUser.getLikeCount(), currentUser.getDislikeCount(), Long.parseLong(editDiscordButton.getText().toString())));
    }
}