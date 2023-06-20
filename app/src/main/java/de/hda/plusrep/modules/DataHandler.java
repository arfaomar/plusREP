package de.hda.plusrep.modules;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hda.plusrep.UserData;


public class DataHandler {
    Postgre db;
    Connection connection;
    Statement statement;

    final String usersColumns = "(steamid, personaname, profileurl, avatarurl, likecount, dislikecount, biography, discordid) ";
    final String friendlistColumns = "(steamid,friendsteamid) ";
    final String forumColumns = "(steamid,postitle,posttext,gameid) ";
    final String gametagsColumns = "(gamename) ";

    public DataHandler() {

        db = new Postgre();
        connection = db.getExtraConnection();
        Log.i("DataHandler: ", "Connection established");
        try {
            statement = connection.createStatement();
        }
        catch (Exception e) {
            Log.e( "DataHandler -> "+e.getClass().getName()+": ", e.getMessage() );
        }

        Log.i("DataHandler: ", "Statement creation success");

    }

    public Statement getStatement() {
        return statement;
    }

    public void addUserToDB(UserData user) {

        Integer steamID = user.getSteamID();
        String personaName = user.getPersonaName();
        String avatarURL = user.getAvatarURL();
        String profileURL = user.getProfileURL();
        String biography = user.getBiography();
        Integer likeCount = user.getLikeCount();
        Integer dislikeCount = user.getDislikeCount();
        Integer discordID = user.getDiscordID();
        String sql = "INSERT INTO USERS " + usersColumns + "VALUES " + "(" + steamID + ", '" +
                personaName + "', '" + avatarURL + "', '" + profileURL + "', '" + biography + "', " +
                likeCount + ", " + dislikeCount + ", " + discordID + ");";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            throw new RuntimeException(e);

        }

    }


    public void updateUserInDB(UserData user) {

        Integer steamID = user.getSteamID();
        String personaName = user.getPersonaName();
        String avatarURL = user.getAvatarURL();
        String profileURL = user.getProfileURL();
        String biography = user.getBiography();
        Integer likeCount = user.getLikeCount();
        Integer dislikeCount = user.getDislikeCount();
        Integer discordID = user.getDiscordID();
        String sql = "UPDATE users SET "  + "personaname = '" + personaName + "', " +
                "profileurl = '" + profileURL + "', " +
                "avatarurl = '" + avatarURL + "', " +
                "likecount = "+ likeCount + ", " +
                "dislikecount = "+ dislikeCount + ", " +
                "biography = '" + biography + "', " +
                "discordid = "+ discordID + " WHERE steamid = " + steamID +";";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (UPDATE)"+e.getClass().getName()+":", e.getMessage());
            throw new RuntimeException(e);

        }

    }


    public UserData getUserFromDB(Integer steamID) {
        String sql = "SELECT * FROM users WHERE steamid = " + steamID + ";";
        UserData user = null;
        ResultSet result;
        try {

            result = statement.executeQuery(sql);

            while (result.next()) {

                String personaName = result.getString("personaname");
                String profileURL = result.getString("profileurl");
                String avatarURL = result.getString("avatarurl");
                Integer likeCount = result.getInt("likecount");
                Integer dislikeCount = result.getInt("dislikecount");
                Integer discordID = result.getInt("discordid");
                String biography = result.getString("biography");

                user = new UserData(steamID, personaName, profileURL, avatarURL, biography, likeCount, dislikeCount, discordID);

            }
            result.close();
        } catch (SQLException e) {
            Log.e("DataHandler -> (GET)"+e.getClass().getName()+":", e.getMessage());
            throw new RuntimeException(e);
        }

        return user;

    }




    @Override
    protected void finalize() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            Log.e("DataHandler -> (CLOSE)"+e.getClass().getName()+":", e.getMessage());
        }
    }

}
