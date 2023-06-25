package de.hda.plusrep;

public class ForumPostModel {

    Long steamID;
    String title;
    String userName;
    String content;
    String gameTag;
    Integer gameTagID;
    Integer postID;

    public ForumPostModel(Long steamID, String title, String userName, String content, String gameTag, Integer gameTagID, Integer postID) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.gameTag = gameTag;
        this.steamID = steamID;
        this.gameTagID = gameTagID;
        this.postID = postID;
    }

    public ForumPostModel(Long steamID, String title, String userName, String content, String gameTag, Integer gameTagID) {
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

    public Long getSteamID() {
        return steamID;
    }

    public Integer getGameTagID() {
        return gameTagID;
    }

    public String getGameTag() {
        return gameTag;
    }
    public Integer getPostID() {
        return postID;
    }
}
