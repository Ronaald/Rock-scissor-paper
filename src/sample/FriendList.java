package sample;

public class FriendList {

    public FriendList(String UserName, int id) {
        this.userName = UserName;
        this.id = id;
    }

    private String userName;
    private int id;



    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }


}