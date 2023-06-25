package de.hda.plusrep;

public class FriendRequestsModel {

    Long steamID;
    String personaName;
    String avatarURL;

    public FriendRequestsModel(Long steamID, String personaName, String avatarURL) {
        this.steamID = steamID;
        this.personaName = personaName;
        this.avatarURL = avatarURL;
    }

    public Long getSteamID() {
        return steamID;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
