package de.hda.plusrep;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class UserData { //ToDo: fill friendsList with Data from JSON. Probably needs new Method

    Integer steamID;
    String personaName;
    String profileURL;
    String avatarURL;
    public Integer likeCount = 0;
    public Integer dislikeCount = 0;
    HashSet<Integer> friendsList = new HashSet<>(); //We are using a HashSet because it's the best collection for random access

    public UserData(Integer steamID, String personaName, String profileURL, String avatarURL) {
        this.steamID = steamID;
        this.personaName = personaName;
        this.profileURL = profileURL;
        this.avatarURL = avatarURL;
    }

    public Integer getSteamID() {
        return steamID;
    }

    final public HashSet<Integer> getFriendsList() {
        return friendsList;
    }

    public Boolean addFriendToList(@NonNull UserData newFriend) {

        Integer friendSteamID = newFriend.getSteamID();
        return this.friendsList.add(friendSteamID);
    }

    public Boolean removeFriendFromList(@NonNull UserData friend) {

        Integer friendSteamID = friend.getSteamID();
        return this.friendsList.remove(friendSteamID);
    }

    public Boolean checkFriendship(@NonNull UserData friend) {
        return friend.friendsList.contains(this.getSteamID()) && this.friendsList.contains(friend.getSteamID());
    }

    public void addLike() {
        this.likeCount++;
    }

    public void addDislike() {
        this.dislikeCount++;
    }

    public void removeLike() {
        this.likeCount--;
    }

    public void removeDislike() {
        this.dislikeCount--;
    }

    public Double calculateRepScore(){
        double totalVotes = (double) likeCount + (double) dislikeCount;
        double avg = (double) likeCount / totalVotes;
        double score = avg - ( avg - 0.5 ) * Math.pow(16,(-Math.log10( ((totalVotes*0.25) + 1) )));

        return score * 100;
    }

}
