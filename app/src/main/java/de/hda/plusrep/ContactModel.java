package de.hda.plusrep;
//DUMMY CLASS
public class ContactModel { //TODO: Find out what User Data the Steam API Provides
    String personaName;
    String repScore;
    String avatarURL;
    Integer steamID;

    public ContactModel(String name, String repScore, String avatarURL) {
        this.personaName = name;
        this.repScore = repScore;
        this.avatarURL = avatarURL;
    }

    public ContactModel(String personaName, String repScore, String avatarURL, Integer steamID) {
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
}
