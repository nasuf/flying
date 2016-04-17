package com.flying.sso.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户信息
 * 
 * @author tao
 *
 */
@Table(name = "tb_userinfo")
public class UserInfo implements java.io.Serializable {

    private static final long serialVersionUID = -3633522550135679426L;

    // 主键id自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 用户名
    @Column(name = "userName")
    private String userName;

    // 密码
    @Column(name = "password")
    private String password;

    // 性别(0为女，1为男)
    @Column(name = "gender")
    private int gender;

    // 年龄
    @Column(name = "age")
    private int age;

    // 手机号
    @Column(name = "phone")
    private String phone;

    // 城市
    @Column(name = "city")
    private String city;

    // 学历
    @Column(name = "degree")
    private String degree;

    // 注册时间
    @Column(name = "registTime")
    private Long registTime;

    // 注册时间-显示
    @Transient
    private String registTime4Display;

    // 本次登陆时间
    @Column(name = "currentLoginTime")
    private Long currentLoginTime;

    // 本次登录时间-显示
    @Transient
    private String currentLoginTime4Display;

    // 上一次登陆时间
    @Column(name = "lastLoginTime")
    private Long lastLoginTime;

    // 上一次登录时间-显示
    @Transient
    private String lastLoginTime4Display;

    // 总在线时长
    @Column(name = "totalOnlineTime")
    private Long totalOnlineTime;

    // 总在线时长-显示
    @Transient
    private String totalOnlineTime4Display;

    // 用户状态
    @Column(name = "status")
    private int status;

    // 积分
    @Column(name = "bonus")
    private Long bonus;

    // 等级
    @Column(name = "level")
    private String level;

    // 用户文章列表
    @Transient
    private List<Essay> essayList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Long getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Long registTime) {
        this.registTime = registTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getTotalOnlineTime() {
        return totalOnlineTime;
    }

    public void setTotalOnlineTime(Long totalOnlineTime) {
        this.totalOnlineTime = totalOnlineTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(Long currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getRegistTime4Display() {
        return registTime4Display;
    }

    public void setRegistTime4Display(String registTime4Display) {
        this.registTime4Display = registTime4Display;
    }

    public String getCurrentLoginTime4Display() {
        return currentLoginTime4Display;
    }

    public void setCurrentLoginTime4Display(String currentLoginTime4Display) {
        this.currentLoginTime4Display = currentLoginTime4Display;
    }

    public String getLastLoginTime4Display() {
        return lastLoginTime4Display;
    }

    public void setLastLoginTime4Display(String lastLoginTime4Display) {
        this.lastLoginTime4Display = lastLoginTime4Display;
    }

    public String getTotalOnlineTime4Display() {
        return totalOnlineTime4Display;
    }

    public void setTotalOnlineTime4Display(String totalOnlineTime4Display) {
        this.totalOnlineTime4Display = totalOnlineTime4Display;
    }

    public List<Essay> getEssayList() {
        return essayList;
    }

    public void setEssayList(List<Essay> essayList) {
        this.essayList = essayList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UserInfo(Long id, String userName, String password, int gender, int age, String phone,
            String city, String degree, Long registTime, Long currentLoginTime, Long lastLoginTime,
            Long totalOnlineTime, int status, Long bonus, String level, List<Essay> essayList) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.city = city;
        this.degree = degree;
        this.registTime = registTime;
        this.currentLoginTime = currentLoginTime;
        this.lastLoginTime = lastLoginTime;
        this.totalOnlineTime = totalOnlineTime;
        this.status = status;
        this.bonus = bonus;
        this.level = level;
        this.essayList = essayList;
    }

    public UserInfo(String userName, String password, int gender, int age, String phone, String city,
            String degree) {
        super();
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.city = city;
        this.degree = degree;
    }

    public UserInfo(Long id, String userName, String password, int gender, int age, String phone,
            String city, String degree, Long registTime, String registTime4Display, Long currentLoginTime,
            String currentLoginTime4Display, Long lastLoginTime, String lastLoginTime4Display,
            Long totalOnlineTime, String totalOnlineTime4Display, int status, Long bonus, String level,
            List<Essay> essayList) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.city = city;
        this.degree = degree;
        this.registTime = registTime;
        this.registTime4Display = registTime4Display;
        this.currentLoginTime = currentLoginTime;
        this.currentLoginTime4Display = currentLoginTime4Display;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginTime4Display = lastLoginTime4Display;
        this.totalOnlineTime = totalOnlineTime;
        this.totalOnlineTime4Display = totalOnlineTime4Display;
        this.status = status;
        this.bonus = bonus;
        this.level = level;
        this.essayList = essayList;
    }

    public UserInfo() {
        super();
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", userName=" + userName + ", password=" + password + ", gender="
                + gender + ", age=" + age + ", phone=" + phone + ", city=" + city + ", degree=" + degree
                + ", registTime=" + registTime + ", currentLoginTime=" + currentLoginTime
                + ", lastLoginTime=" + lastLoginTime + ", totalOnlineTime=" + totalOnlineTime + ", status="
                + status + ", bonus=" + bonus + ", level=" + level + ", essayList=" + essayList + "]";
    }

}
