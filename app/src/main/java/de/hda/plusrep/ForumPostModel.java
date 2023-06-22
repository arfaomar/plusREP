package de.hda.plusrep;

public class ForumPostModel {

    Integer steamID;
    String title;
    String userName;
    String content;
    String gameTag;
    Integer gameTagID;
    public ForumPostModel(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }

    public ForumPostModel(Integer steamID, String title, String userName, String content, String gameTag, Integer gameTagID) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.gameTag = gameTag;
        this.steamID = steamID;
        this.gameTagID = gameTagID;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSteamID() {
        return steamID;
    }

    public Integer getGameTagID() {
        return gameTagID;
    }

    public String getGameTag() {
        return gameTag;
    }
}
