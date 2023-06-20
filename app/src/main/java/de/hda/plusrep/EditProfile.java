package de.hda.plusrep;

import static android.widget.TextView.BufferType.EDITABLE;
import static android.widget.TextView.BufferType.NORMAL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    Intent activeIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        activeIntent = getIntent();

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
    }

    private void updateUserData(){
        EditText editText = findViewById(R.id.editBiographyText);
        activeIntent.putExtra("updated Bio", editText.getText().toString());
    }
}