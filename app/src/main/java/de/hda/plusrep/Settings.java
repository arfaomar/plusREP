package de.hda.plusrep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

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
    }
}