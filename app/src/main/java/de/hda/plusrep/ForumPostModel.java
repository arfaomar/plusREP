package de.hda.plusrep;

public class ForumPostModel {
    String title;
    String userName;
    String avatarURL;
    String content;

    public ForumPostModel(String title, String userName, String avatarURL) {
        this.title = title;
        this.userName = userName;
        this.avatarURL = avatarURL;
    }

    public ForumPostModel(String title, String userName, String avatarURL, String content) {
        this.title = title;
        this.userName = userName;
        this.avatarURL = avatarURL;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
