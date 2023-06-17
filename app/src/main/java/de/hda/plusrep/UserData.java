package de.hda.plusrep;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class UserData { //ToDo: fill friendsList with Data from JSON. Probably needs new Method

    String steamID;
    String personaName;
    String profileURL;
    String avatarURL;
    HashSet<Integer> friendsList = new HashSet<>(); //We are using a HashSet because it's the best collection for random access

    public UserData(String steamID, String personaName, String profileURL, String avatarURL) {
        this.steamID = steamID;
        this.personaName = personaName;
        this.profileURL = profileURL;
        this.avatarURL = avatarURL;
    }

    public String getSteamID() {
        return steamID;
    }

    public Integer getSteamIDInteger() {
        return Integer.parseInt(this.steamID);
    }

    public HashSet<Integer> getFriendsList() {
        return friendsList;
    }

    public Boolean addFriendToList(@NonNull UserData newFriend) {

        Integer friendSteamID = newFriend.getSteamIDInteger();

        boolean friendIsAlreadyInList = this.friendsList.contains(friendSteamID);

        if (!friendIsAlreadyInList){
            this.friendsList.add(friendSteamID);
            return true;
        }

        return false;
    }

    public Boolean removeFriendFromList(@NonNull UserData friend) {
        Integer friendSteamID = friend.getSteamIDInteger();

        return this.friendsList.remove(friendSteamID);
    }


    public boolean checkFriendship(@NonNull UserData friend) {
        return friend.friendsList.contains(this.getSteamIDInteger()) && this.friendsList.contains(friend.getSteamIDInteger());
    }
}
