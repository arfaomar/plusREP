package de.hda.plusrep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView navbar;
        navbar = findViewById(R.id.bottomNavbar);
        navbar.setSelectedItemId(R.id.profileButton);

        getWindow().setStatusBarColor(getColor(R.color.black)); // Makes the status bar black

        // + Switchable buttons (changes alpha after clicking)
        final boolean[] disliked = {false};
        final boolean[] liked = {false};
        findViewById(R.id.dislikeButton).setAlpha((float)0.3);
        findViewById(R.id.likeButton).setAlpha((float)0.3);
        ImageButton likeButton = findViewById(R.id.likeButton);
        ImageButton dislikeButton = findViewById(R.id.dislikeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liked[0] = !liked[0];
                if (liked[0]) {
                    v.setAlpha((float) 1);
                    disliked[0] = false;
                    findViewById(R.id.dislikeButton).setAlpha((float)0.3);
                }
                else v.setAlpha((float)0.5);
                Log.e("AAAAAAAH", "Like: " + liked[0]);
                Log.e("AAAAAAAH", "Dislke: " + disliked[0]);

            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disliked[0] = !disliked[0];
                if (disliked[0]) {
                    v.setAlpha((float) 1);
                    liked[0] = false;
                    findViewById(R.id.likeButton).setAlpha((float)0.3);
                }
                else v.setAlpha((float)0.5);
                Log.e("AAAAAAAH", "Like: " + liked[0]);
                Log.e("AAAAAAAH", "Dislke: " + disliked[0]);
            }
        });
        // - Switchable buttons


        navbar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.profileButton) {
                return true;
            }

            if (item.getItemId() == R.id.forumButton) {
                startActivity(new Intent(getApplicationContext(), Forum.class));
                overridePendingTransition(0, 0);
                //finish();
                return true;
            }

            if (item.getItemId() == R.id.contactsButton) {
                startActivity(new Intent(getApplicationContext(), Contacts.class));
                overridePendingTransition(0, 0);
                //finish();
                return true;
            }

            if (item.getItemId() == R.id.settingsButton) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
                overridePendingTransition(0,0);
                //finish();
                return true;
            }

            return false;
        });

    }








}