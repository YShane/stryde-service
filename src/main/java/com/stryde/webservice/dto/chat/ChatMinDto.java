package com.stryde.webservice.dto.chat;

import java.time.LocalDateTime;


public class ChatMinDto {

    private Long userid;
    private String userfirstname;
    private String userlastname;
    private String username;
    private String profilephotolink;
    private LocalDateTime ddob;

    public Long getUserId() {
        return userid;
    }

    public void setUserId(Long userId) {
        this.userid = userId;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilephotolink() {
        return profilephotolink;
    }

    public void setProfilephotolink(String profilephotolink) {
        this.profilephotolink = profilephotolink;
    }

    public LocalDateTime getDdob() {
        return ddob;
    }

    public void setDdob(LocalDateTime ddob) {
        this.ddob = ddob;
    }

    @Override
    public String toString() {
        return "ChatMinDto{" +
                "userId=" + userid +
                ", userfirstname='" + userfirstname + '\'' +
                ", userlastname='" + userlastname + '\'' +
                ", username='" + username + '\'' +
                ", profilephotolink='" + profilephotolink + '\'' +
                ", ddob=" + ddob +
                '}';
    }
}
