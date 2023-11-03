package com.example.whats_app.Modal;

public class Users {
    String progilepic;
    String name;

    public String getProgilepic() {
        return progilepic;
    }

    public void setProgilepic(String progilepic) {
        this.progilepic = progilepic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public Users(String progilepic, String name, String mail, String password, String userid, String lastmessage) {
        this.progilepic = progilepic;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.userid = userid;
        this.lastmessage = lastmessage;
    }

    // empty constractor firebase me dena padta hai
    public Users() {
    }

    // Constractor for password ana email
    public Users(String name, String mail, String password) {

        this.name = name;
        this.mail = mail;
        this.password = password;

    }

    String mail;
    String password;
    String userid;
    String lastmessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

}
