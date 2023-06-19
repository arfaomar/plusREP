package de.hda.plusrep;

import static android.widget.TextView.BufferType.EDITABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        setUserData();
    }

    private void setUserData(){
        EditText editText = findViewById(R.id.editBiographyText);
        TextView userBio = findViewById(R.id.biographyText);
        editText.setText(userBio.getText(), EDITABLE);
    }
}