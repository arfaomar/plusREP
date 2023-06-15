package de.hda.plusrep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hda.plusrep.modules.Navbar;

public class Profile extends AppCompatActivity {

    Intent activeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(getColor(R.color.black));



        activeIntent = getIntent();
        String steamID = activeIntent.getStringExtra("Login.steamID");

        getUserData(steamID);


        Navbar navbar = new Navbar(this, activeIntent);
        navbar.createNavbar();
    }

    private void getUserData(String steamID) {

        // Falls schon geladen überspringe
        if (activeIntent.getStringExtra("Profile.loaded").equals("TRUE")) {

            try {

                update(steamID);
                return;

            } catch (JSONException e) {

                throw new RuntimeException(e);
            }
        }

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
                        activeIntent.putExtra("User." + steamID + ".name", user.get("personaname").toString());
                        activeIntent.putExtra("User." + steamID + ".url", user.get("avatarfull").toString());

                        activeIntent.putExtra("Profile.loaded", "TRUE");
                        update(steamID);
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

                Log.e("SteamAPI", "Error: " + volleyError.toString());
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Profile.this);
        rQueue.add(request);
    }

    private void update(String steamID) throws JSONException {

        TextView username = findViewById(R.id.username);
        username.setText(activeIntent.getStringExtra("User." + steamID + ".name"));

        ImageView userAvatar = findViewById(R.id.userAvatar);
        Glide.with(getApplicationContext()).load(activeIntent.getStringExtra("User." + steamID + ".url"))
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(userAvatar);

    }
}