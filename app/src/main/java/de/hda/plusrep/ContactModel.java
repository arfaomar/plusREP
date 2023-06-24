package de.hda.plusrep;
//DUMMY CLASS
public class ContactModel { //TODO: Find out what User Data the Steam API Provides
    String personaName;
    String repScore;
    String avatarURL;
    Long steamID;

    public ContactModel(String personaName, String repScore, String avatarURL, Long steamID) {
        this.personaName = personaName;
        this.repScore = repScore;
        this.avatarURL = avatarURL;
        this.steamID = steamID;
    }

    public String getName() {
        return personaName;
    }

    public String getRepScore() {
        return repScore;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public Long getSteamID() {
        return steamID;
    }

}
