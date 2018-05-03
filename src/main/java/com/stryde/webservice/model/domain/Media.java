package com.stryde.webservice.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "media")
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String C_Id = "id";
    private static final String C_userId = "user_id";
    private static final String C_profilePhotoThumbnailLink = "profilephotothumbnail_link";

    @Column(name= C_Id, nullable = false)@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @JoinColumn(name = C_userId)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = C_profilePhotoThumbnailLink)
    private String profilePhotoThumbnailLink;


    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getProfilePhotoThumbnailLink() {
        return profilePhotoThumbnailLink;
    }

    public void setProfilePhotoThumbnailLink(String profilePhotoThumbnailLink) {
        this.profilePhotoThumbnailLink = profilePhotoThumbnailLink;
    }

    public User getUser() {
        if(user!=null){
            return user;
        }else {
            return new User();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
