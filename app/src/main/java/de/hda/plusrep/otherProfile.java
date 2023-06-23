package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hda.plusrep.modules.Navbar;

public class otherProfile extends AppCompatActivity {

    Intent activeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));

        this.setupButtons();

        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }


    private void setupButtons() {

        ImageButton likeButton = findViewById(R.id.otherLikeButton);
        ImageButton dislikeButton = findViewById(R.id.otherDislikeButton);

        // on setup => rating buttons have opacity of 30%
        findViewById(R.id.otherLikeButton).setAlpha((float)0.3);
        findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);

        final boolean[] disliked = {false};
        final boolean[] liked = {false};

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liked[0] = !liked[0];
                if (liked[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);
                    disliked[0] = false;
                }
                else v.setAlpha((float)0.5);
            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disliked[0] = !disliked[0];
                if (disliked[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherLikeButton).setAlpha((float)0.3);
                    liked[0] = false;
                }
                else v.setAlpha((float)0.5);
            }
        });
    }

}
