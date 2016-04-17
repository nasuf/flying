package com.flying.cms.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_essay")
public class Essay {

    @Column(name = "userId")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "essayId")
    private Long essayId;

    @Column(name = "title")
    private String title;

    @Column(name = "subTitle")
    private String subTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "saveDate")
    private Long saveDate;

    @Column(name = "pushDate")
    private Long pushDate;

    @Column(name = "status")
    private int status;

    @Column(name = "type")
    private String type;

    @Transient
    private UserInfo userInfo;

    public Essay() {
        super();
    }

    public Essay(Long userId, Long essayId, String title, String subTitle, String author, Long saveDate,
            Long pushDate, int status, String type) {
        super();
        this.userId = userId;
        this.essayId = essayId;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.saveDate = saveDate;
        this.pushDate = pushDate;
        this.status = status;
        this.type = type;
    }

    public Essay(Long userId, Long essayId, String title, String subTitle, String author, Long saveDate,
            Long pushDate, int status, String type, UserInfo userInfo) {
        super();
        this.userId = userId;
        this.essayId = essayId;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.saveDate = saveDate;
        this.pushDate = pushDate;
        this.status = status;
        this.type = type;
        this.userInfo = userInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEssayId() {
        return essayId;
    }

    public void setEssayId(Long essayId) {
        this.essayId = essayId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Long saveDate) {
        this.saveDate = saveDate;
    }

    public Long getPushDate() {
        return pushDate;
    }

    public void setPushDate(Long pushDate) {
        this.pushDate = pushDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Essay [userId=" + userId + ", essayId=" + essayId + ", title=" + title + ", subTitle="
                + subTitle + ", author=" + author + ", saveDate=" + saveDate + ", pushDate=" + pushDate
                + ", status=" + status + ", type=" + type + ", userInfo=" + userInfo + "]";
    }

}
