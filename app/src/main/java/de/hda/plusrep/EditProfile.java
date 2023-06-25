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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        activeIntent = getIntent();
        ImageView avatar = findViewById(R.id.editUserAvatar);
        TextView username = findViewById(R.id.editUsername);
        DataHandler dataHandler = DataHandler.getDataHandlerInstance();
        UserData userData = dataHandler.getUserFromDB(Long.parseLong(Profile.steamID));
        Glide.with(this).load(userData.getAvatarURL())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(avatar);
        username.setText(userData.getPersonaName());

        readUserData();
        ImageButton saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
                activeIntent.setIdentifier("editProfile");
                activeIntent.setClass(saveButton.getContext(), Profile.class);
                startActivity(activeIntent);
            }
        });
    }

    private void readUserData(){
        EditText editText = findViewById(R.id.editBiographyText);
        String currentBio = activeIntent.getStringExtra("Current Bio");
        editText.setText(currentBio, EDITABLE);

        EditText editDiscordText = findViewById(R.id.editDiscordButtonText);
        String currentDiscord = activeIntent.getStringExtra("Current Discord");
        if(!"DISCORD".equals(currentDiscord)) {
            editDiscordText.setText(currentDiscord, EDITABLE);
        }
    }

    private void updateUserData(){
        EditText editText = findViewById(R.id.editBiographyText);
        Button editDiscordButton = findViewById(R.id.editDiscordButton);
        UserData userData = dataHandler.getUserFromDB(Long.parseLong(Profile.steamID));
        dataHandler.updateUserInDB(new UserData(userData.getSteamID(), userData.getPersonaName(), userData.getProfileURL(), userData.getAvatarURL(), editText.getText().toString(), userData.getLikeCount(), userData.getDislikeCount(), Long.parseLong(editDiscordButton.getText().toString())));
    }
}