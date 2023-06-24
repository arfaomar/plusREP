package de.hda.plusrep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import de.hda.plusrep.modules.Navbar;

public class Settings extends AppCompatActivity {

    Intent activeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));



        activeIntent = getIntent();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();

        Button gerLangButton = findViewById(R.id.gerLangButton);
        gerLangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageHelper.setAppLocale("de", Settings.this);
            }
        });
    }
}