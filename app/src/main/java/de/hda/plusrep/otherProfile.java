package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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

import java.util.ArrayList;

import de.hda.plusrep.modules.DataHandler;
import de.hda.plusrep.modules.Navbar;

public class otherProfile extends AppCompatActivity {

    private Intent activeIntent;
    private String steamID;
    private UserData userData;
    private DataHandler dataHandler = DataHandler.getDataHandlerInstance();
    ArrayList<Boolean> likeState = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));


        activeIntent = getIntent();
        steamID = activeIntent.getStringExtra("Profile.steamID");
        likeState = dataHandler.getRatedFromDBforUser(Long.parseLong(Profile.steamID), Long.parseLong(steamID));
        this.setupButtons();

        getUserData();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }

    private void setupButtons() {

        // Get buttons
        ImageButton likeButton = findViewById(R.id.otherLikeButton);
        ImageButton dislikeButton = findViewById(R.id.otherDislikeButton);
        ImageButton addFriendButton = findViewById(R.id.addFriendButton);

        // on setup => rating buttons have opacity of 30%
        findViewById(R.id.otherLikeButton).setAlpha((float)0.3);
        findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);
        findViewById(R.id.addFriendButton).setAlpha((float)0.3);

        final boolean[] disliked = {false};
        final boolean[] liked = {false};
        final boolean[] added = {false};

        // If friend...
        // [...]

        // Get like state from database
        if (likeState.get(0)) {

            liked[0] = true;
            findViewById(R.id.otherLikeButton).setAlpha((float)1);
        }

        if (likeState.get(1)) {

            disliked[0] = true;
            findViewById(R.id.otherDislikeButton).setAlpha((float)1);
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liked[0] = !liked[0];
                if (liked[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);
                    if (disliked[0]) {
                        userData.removeDislike();
                        disliked[0] = false;
                    }

                    userData.addLike();
                    dataHandler.setRatedForUserInDB(Long.parseLong(Profile.steamID), Long.parseLong(steamID), true, false);
                }
                else {
                    userData.removeLike();
                    dataHandler.setRatedForUserInDB(Long.parseLong(Profile.steamID), Long.parseLong(steamID), false, false);
                    v.setAlpha((float)0.3);
                }
                Log.i("OtherProfile", "Like (" + userData.getLikeCount() + ")");
                update();
                dataHandler.updateUserInDB(userData);
            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disliked[0] = !disliked[0];
                if (disliked[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherLikeButton).setAlpha((float)0.3);
                    if (liked[0]) {
                        userData.removeLike();
                        liked[0] = false;
                    }

                    userData.addDislike();
                    dataHandler.setRatedForUserInDB(Long.parseLong(Profile.steamID), Long.parseLong(steamID), false, true);
                }
                else {
                    userData.removeDislike();
                    dataHandler.setRatedForUserInDB(Long.parseLong(Profile.steamID), Long.parseLong(steamID), false, false);
                    v.setAlpha((float) 0.3);
                }
                Log.i("OtherProfile", "Dislike (" + userData.getDislikeCount() + ")");
                update();
                dataHandler.updateUserInDB(userData);
            }
        });
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                added[0] = !added[0];
                if (added[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherLikeButton).setAlpha((float)0.3);

                    if (dataHandler.sendFriendRequest(Long.parseLong(steamID), Long.parseLong(Profile.steamID)));
                    else {

                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                        Log.e("OtherProfile", "An error occurred");
                    }
                }
                else {
                    v.setAlpha((float) 0.3);

                    if (dataHandler.removeFriendRequest(Long.parseLong(Profile.steamID), Long.parseLong(steamID)));
                    else {

                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                        Log.e("OtherProfile", "An error occurred");
                    }
                }

                update();
            }
        });
    }

    protected void update() {

        // Get view items
        TextView username = findViewById(R.id.otherUsername);
        TextView biographyText = findViewById(R.id.otherBiographyText);
        ImageView userAvatar = findViewById(R.id.otherUserAvatar);
        TextView repScore = findViewById(R.id.repScore);

        // Set username
        username.setText(userData.getPersonaName());

        // Set biography
        biographyText.setText(userData.getBiography());

        // Set avatar
        Glide.with(getApplicationContext()).load(userData.getAvatarURL())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(userAvatar);

        // Set score (if no (dis)likes the string is "-")
        if (userData.getLikeCount() == 0 && userData.getDislikeCount() == 0) repScore.setText("-");
        else repScore.setText(""+Math.round(userData.calculateRepScore()));
    }

    protected void getUserData() { // Get data from database

        userData = dataHandler.getUserFromDB(Long.parseLong(steamID));
        if (userData == null) {

            // Not found
            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
            Log.e("OtherProfile", "User does not exist");
        }
        else {

            // Found
            update();
            Log.i("OtherProfile", "Successfully loaded user from database");
        }
    }
}
