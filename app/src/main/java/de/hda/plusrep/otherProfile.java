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

import de.hda.plusrep.modules.DataHandler;
import de.hda.plusrep.modules.Navbar;

public class otherProfile extends AppCompatActivity {

    private Intent activeIntent;
    private String steamID;
    private UserData userData;
    private DataHandler dataHandler = DataHandler.getDataHandlerInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));


        activeIntent = getIntent();
        steamID = activeIntent.getStringExtra("Profile.steamID");
        this.setupButtons();



        getUserData();
        update();


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }

    public void updateRepScore() {

        TextView repScore = findViewById(R.id.repScore);
        repScore.setText(""+Math.round(userData.calculateRepScore()));
    }

    private void setupButtons() {

        ImageButton likeButton = findViewById(R.id.otherLikeButton);
        ImageButton dislikeButton = findViewById(R.id.otherDislikeButton);

        // on setup => rating buttons have opacity of 30%
        findViewById(R.id.otherLikeButton).setAlpha((float)0.3);
        findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);

        final boolean[] disliked = {false};
        final boolean[] liked = {false};

        if (activeIntent.getStringExtra("Profile." + steamID + ".loaded").equals("TRUE")) {

            if (activeIntent.getStringExtra("Profile." + steamID + ".liked").equals("TRUE")) {

                liked[0] = true;
                findViewById(R.id.otherLikeButton).setAlpha((float)1);
            }

            if (activeIntent.getStringExtra("Profile." + steamID + ".disliked").equals("TRUE")) {

                disliked[0] = true;
                findViewById(R.id.otherDislikeButton).setAlpha((float)1);
            }
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liked[0] = !liked[0];
                if (liked[0]) {
                    v.setAlpha((float) 1);
                    findViewById(R.id.otherDislikeButton).setAlpha((float)0.3);
                    disliked[0] = false;

                    userData.addLike();
                    activeIntent.putExtra("Profile." + steamID + ".liked", "TRUE");
                }
                else {
                    userData.removeLike();
                    activeIntent.putExtra("Profile." + steamID + ".liked", "FALSE");
                    v.setAlpha((float)0.3);
                }
                Log.i("OtherProfile", "Like (" + userData.getLikeCount() + ")");
                updateRepScore();
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
                    liked[0] = false;

                    userData.addDislike();
                    activeIntent.putExtra("Profile." + steamID + ".disliked", "TRUE");
                }
                else {
                    userData.removeDislike();
                    activeIntent.putExtra("Profile." + steamID + ".disliked", "FALSE");
                    v.setAlpha((float) 0.3);
                }
                Log.i("OtherProfile", "Dislike (" + userData.getDislikeCount() + ")");
                updateRepScore();
                dataHandler.updateUserInDB(userData);
            }
        });
    }

    protected void update() {

        TextView username = findViewById(R.id.otherUsername);
        username.setText(activeIntent.getStringExtra("User." + steamID + ".personaName"));

        TextView biographyText = findViewById(R.id.otherBiographyText);
        if (dataHandler.getUserFromDB(Long.parseLong(steamID)) != null) biographyText.setText(dataHandler.getUserFromDB(Long.parseLong(steamID)).getBiography());

        ImageView userAvatar = findViewById(R.id.otherUserAvatar);
        Glide.with(getApplicationContext()).load(activeIntent.getStringExtra("User." + steamID + ".avatarURL"))
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(userAvatar);
    }

    protected void getUserData() {

        // Falls schon geladen überspringe
        if (activeIntent.getStringExtra("Profile." + steamID + ".loaded").equals("TRUE")) return;

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        StringRequest request = new StringRequest("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=973AE865DE90E85B6BEB1B3C792BCF58&steamids="+steamID, new Response.Listener<String>() {

            @Override
            public void onResponse(String string) {

                try {

                    JSONObject json = new JSONObject(string);
                    JSONArray jsonArray = json.getJSONObject("response").getJSONArray("players");

                    if (jsonArray.length() > 0) {

                        JSONObject user = jsonArray.getJSONObject(0);

                        // Save in intent
                        activeIntent.putExtra("User." + steamID + ".personaName", user.get("personaname").toString());
                        activeIntent.putExtra("User." + steamID + ".profileURL", user.get("profileurl").toString());
                        activeIntent.putExtra("User." + steamID + ".avatarURL", user.get("avatarfull").toString());

                        // Save in database

                        if (dataHandler.getUserFromDB(Long.parseLong(steamID)) == null) {

                            userData = new UserData(Long.parseLong(steamID), user.get("personaname").toString(), user.get("profileurl").toString(), user.get("avatarfull").toString(), "", 0, 0, null);
                            dataHandler.addUserToDB(userData);

                            Toast.makeText(getApplicationContext(), "User added to database", Toast.LENGTH_SHORT).show(); // DEBUG
                            Log.i("OtherProfile", "User added to database");
                        }
                        else {

                            String userBiography = dataHandler.getUserFromDB(Long.parseLong(steamID)).getBiography();
                            int likes =  dataHandler.getUserFromDB(Long.parseLong(steamID)).getLikeCount();
                            int dislikes = dataHandler.getUserFromDB(Long.parseLong(steamID)).getDislikeCount();
                            userData = new UserData(Long.parseLong(steamID), user.get("personaname").toString(), user.get("profileurl").toString(), user.get("avatarfull").toString(), userBiography, likes, dislikes, null);
                            dataHandler.updateUserInDB(userData);

                            Toast.makeText(getApplicationContext(), "User updated to database", Toast.LENGTH_SHORT).show(); // DEBUG
                            Log.i("OtherProfile", "User updated to database");
                        }

                        activeIntent.putExtra("Profile." + steamID + ".loaded", "TRUE");
                        updateRepScore();
                        update();
                    }

                } catch (JSONException e) {

                    throw new RuntimeException(e);
                }

                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getApplicationContext(), "User konnte nicht geladen werden", Toast.LENGTH_SHORT).show();

                Log.e("OtherProfile", "Error: " + volleyError.toString());
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
        rQueue.add(request);
    }
}
