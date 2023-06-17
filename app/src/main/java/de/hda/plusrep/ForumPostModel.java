package de.hda.plusrep;

public class ForumPostModel {
    String title;
    String userName;
    String avatarURL;

    public ForumPostModel(String title, String userName, String avatarURL) {
        this.title = title;
        this.userName = userName;
        this.avatarURL = avatarURL;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
