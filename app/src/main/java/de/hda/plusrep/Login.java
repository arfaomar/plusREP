package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.hda.plusrep.modules.SteamAPI;

public class Login extends AppCompatActivity {

    public Intent activeIntent; // Ein Intent der fürs Speichern der Screen-Informationen genutzt wird
    private SteamAPI steamAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(getColor(R.color.black));



        activeIntent = new Intent(getApplicationContext(), Profile.class);
        steamAPI = new SteamAPI(getApplicationContext());



        setupButtons();
    }

    private void setupButtons() {

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                steamAPI.login(Login.this);
            }
        });
    }
}