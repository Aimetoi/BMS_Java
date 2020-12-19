package entity;

/**
 * @Author: Aime-toi
 * @Project: User
 * @Version: 1.0
 * @Date: 2020-07-08 13:33
 * @Description:
 **/
public class User {

    private int id;
    private String userName;
    private String password;
    private int admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
