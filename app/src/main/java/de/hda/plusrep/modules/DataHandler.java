package de.hda.plusrep.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hda.plusrep.ForumPostModel;
import de.hda.plusrep.UserData;


public class DataHandler {
    private static final DataHandler dataHandler = new DataHandler();
    Postgre db;
    Connection connection;
    Statement statement;

    private static final String usersColumns = "(steamid, personaname, profileurl, avatarurl, likecount, dislikecount, biography, discordid) ";
    private static final String friendlistColumns = "(steamid,friendsteamid) ";
    private static final String forumColumns = "(steamid,postitle,posttext,gameid,username) ";
    private static final String gametagsColumns = "(gamename) ";

    private DataHandler() {

        db = new Postgre();
        connection = db.getCon();
        Log.i("DataHandler: ", "Connection established");
        try {
            statement = connection.createStatement();
        }
        catch (Exception e) {
            Log.e( "DataHandler (Create Statement) -> "+e.getClass().getName()+": ", e.getMessage() );
        }

        Log.i("DataHandler: ", "Statement creation success");

    }

    public static DataHandler getDataHandlerInstance(){ return dataHandler; }

    public Statement getStatement() {
        return statement;
    }

    public Boolean addUserToDB(@NonNull UserData user) {

        Integer steamID = user.getSteamID();
        String personaName = user.getPersonaName();
        String avatarURL = user.getAvatarURL();
        String profileURL = user.getProfileURL();
        String biography = user.getBiography();
        Integer likeCount = user.getLikeCount();
        Integer dislikeCount = user.getDislikeCount();
        Integer discordID = user.getDiscordID();
        String sql = "INSERT INTO users " + usersColumns + "VALUES " + "(" + steamID + ", '" +
                personaName + "', '" + avatarURL + "', '" + profileURL + "', '" + biography + "', " +
                likeCount + ", " + dislikeCount + ", " + discordID + ");";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            return false;

        }
        return true;
    }

    public Boolean updateUserInDB(@NonNull UserData user) {
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
            return false;

        }

        return true;
    }

    public UserData getUserFromDB(@NonNull Integer steamID) {

        String sql = "SELECT * FROM users WHERE steamid = " + steamID + ";";

        UserData user = null;
        ResultSet result;

        try {

            result = statement.executeQuery(sql);

            String personaName = null;
            String profileURL = null;
            String avatarURL = null;
            Integer likeCount = null;
            Integer dislikeCount = null;
            Integer discordID = null;
            String biography = null;

            while (result.next()) {

                personaName = result.getString("personaname");
                profileURL = result.getString("profileurl");
                avatarURL = result.getString("avatarurl");
                likeCount = result.getInt("likecount");
                dislikeCount = result.getInt("dislikecount");
                discordID = result.getInt("discordid");
                biography = result.getString("biography");

            }

            user = new UserData(steamID, personaName, profileURL, avatarURL, biography, likeCount, dislikeCount, discordID);

            result = statement.executeQuery("SELECT friendsteamid FROM friendlist WHERE steamid = " + steamID );

            while (result.next()) {
                Integer friendSteamID = result.getInt("friendsteamid");
                user.addFriendToList(friendSteamID);
                result.close();
            }





        } catch (SQLException e) {
            Log.e("DataHandler -> (GET)"+e.getClass().getName()+":", e.getMessage());
            user = null;
        }

        return user;

    }

    public Boolean addFriendToDB(Integer userSteamID, Integer friendSteamID){

        String sql = "INSERT INTO friendlist " + friendlistColumns + "VALUES " + "(" + userSteamID + ", " + friendSteamID + ");";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            return false;

        }
        return true;
    }

    public Boolean deleteUserFromDB(@NonNull Integer steamID) {
        String sql = "DELETE from users WHERE steamid = " + steamID + ";";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            return false;

        }

        return true;
    }

    public ArrayList<ForumPostModel> getPostsFromDB() {

        String sql = "SELECT * FROM forum ;";

        Integer steamID;
        String title;
        String userName;
        String content;
        Integer gameID;
        String gameName = "";

        ResultSet result;
        ResultSet gametag = null;
        Statement temp;

        ArrayList<ForumPostModel> forumPosts = new ArrayList<>();

        try{

            result = statement.executeQuery(sql);
            temp = connection.createStatement();
            while(result.next()) {

                steamID = result.getInt("steamid");
                title = result.getString("posttitle");
                userName = result.getString("username");
                content = result.getString("posttext");
                gameID = result.getInt("gameid");

                gametag = temp.executeQuery("SELECT gamename FROM gametags WHERE gameid = " + gameID + ";");

                while (gametag.next()) {
                    gameName = gametag.getString("gamename");
                    gametag.close();
                }

                forumPosts.add(new ForumPostModel(steamID,title,userName,content,gameName,gameID));

            }


            result.close();

        } catch (SQLException e) {
            Log.e("DataHandler -> (GET)"+e.getClass().getName()+":", e.getMessage());
            forumPosts = null;
        }

        return forumPosts;
    }

    public Boolean addPostToDB(@NonNull ForumPostModel post) {

        String title = post.getTitle();
        String userName = post.getUserName();
        Integer steamID = post.getSteamID();
        String content = post.getContent();
        Integer gameTagID = post.getGameTagID();
        //idk how to get the contents of Forum

        String sql = "INSERT INTO forum " + forumColumns + "VALUES " + "(" + steamID + ", '" + title + "', '" +
                content + "', " + gameTagID + ", '" + userName + "');";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            return false;

        }

        return true;
    }

    public Boolean deleteUserPostToDB(@NonNull Integer steamID) {
        String sql = "DELETE from forum WHERE steamid = " + steamID + ";";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Log.e("DataHandler -> (INSERT) "+e.getClass().getName()+":", e.getMessage());
            return false;

        }

        return true;
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
