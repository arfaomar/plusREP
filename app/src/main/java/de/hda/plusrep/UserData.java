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

    final public Integer getSteamIDInteger() {
        return Integer.parseInt(this.steamID);
    }

    final public HashSet<Integer> getFriendsList() {
        return friendsList;
    }

    public Boolean addFriendToList(@NonNull UserData newFriend) {

        Integer friendSteamID = newFriend.getSteamIDInteger();
        return this.friendsList.add(friendSteamID);
    }

    public Boolean removeFriendFromList(@NonNull UserData friend) {

        Integer friendSteamID = friend.getSteamIDInteger();
        return this.friendsList.remove(friendSteamID);
    }

    public Boolean checkFriendship(@NonNull UserData friend) {
        return friend.friendsList.contains(this.getSteamIDInteger()) && this.friendsList.contains(friend.getSteamIDInteger());
    }

}
