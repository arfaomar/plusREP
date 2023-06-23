package de.hda.plusrep;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class UserData { //ToDo: fill friendsList with Data from JSON. Probably needs new Method

    private Long steamID;
    private String personaName;
    private String profileURL;
    private String biography;
    private String avatarURL;
    private Integer likeCount;
    private Integer dislikeCount;
    private Long discordID;

    private HashSet<Long> friendsList = new HashSet<>(); //We are using a HashSet because it's the best collection for random access

    public UserData(Long steamID, String personaName, String profileURL, String avatarURL, String biography) {
        this.steamID = steamID;
        this.personaName = personaName;
        this.profileURL = profileURL;
        this.avatarURL = avatarURL;
        this.biography = biography;
    }

    public UserData(Long steamID, String personaName, String profileURL, String avatarURL, String biography, Integer likeCount, Integer dislikeCount, Long discordID) {
        this.steamID = steamID;
        this.personaName = personaName;
        this.profileURL = profileURL;
        this.biography = biography;
        this.avatarURL = avatarURL;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.discordID = discordID;
    }

    public Long getSteamID() {
        return steamID;
    }

    final public HashSet<Long> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(HashSet<Long> friendsList) {
        this.friendsList = friendsList;
    }

    public Boolean addFriendToList(@NonNull UserData newFriend) {

        Long friendSteamID = newFriend.getSteamID();
        return this.friendsList.add(friendSteamID);
    }

    public Boolean addFriendToList(@NonNull Long newFriendSteamID) {

        return this.friendsList.add(newFriendSteamID);
    }

    public Boolean removeFriendFromList(@NonNull UserData friend) {

        Long friendSteamID = friend.getSteamID();
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


    public String getPersonaName() {
        return personaName;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getBiography() {
        return biography;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public Long getDiscordID() {
        return discordID;
    }


    public Double calculateRepScore(){
        double totalVotes = (double) likeCount + (double) dislikeCount;
        double avg = (double) likeCount / totalVotes;
        double score = avg - ( avg - 0.5 ) * Math.pow(16,(-Math.log10( ((totalVotes*0.25) + 1) )));

        return score * 100;
    }

}
