package com.example.shoppingapp.model;

public class Guest {

    private int id;
    private String guestname;
    private Integer guestphone;
    private String guestemail;

    public Guest() {
    }

    public Guest(int id, String guestname, Integer guestphone, String guestemail) {
        this.id = id;
        this.guestname = guestname;
        this.guestphone = guestphone;
        this.guestemail = guestemail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public Integer getGuestphone() {
        return guestphone;
    }

    public void setGuestphone(Integer guestphone) {
        this.guestphone = guestphone;
    }

    public String getGuestemail() {
        return guestemail;
    }

    public void setGuestemail(String guestemail) {
        this.guestemail = guestemail;
    }
}
