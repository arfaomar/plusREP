package de.hda.plusrep;
//DUMMY CLASS
public class ContactModel { //TODO: Find out what User Data the Steam API Provides
    String personaName;
    String repScore;
    String avatarURL;

    public ContactModel(String name, String repScore, String avatarURL) {
        this.personaName = name;
        this.repScore = repScore;
        this.avatarURL = avatarURL;
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
