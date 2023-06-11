package de.hda.plusrep;
//DUMMY CLASS
public class ContactModel { //TODO: Find out what User Data the Steam API Provides
    String name;
    String repScore;
    int profilePic;

    public ContactModel(String name, String repScore, int profilePic) {
        this.name = name;
        this.repScore = repScore;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getRepScore() {
        return repScore;
    }

    public int getProfilePic() {
        return profilePic;
    }
}
