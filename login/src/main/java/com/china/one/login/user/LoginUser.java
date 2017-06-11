package com.china.one.login.user;

/**
 * 用户信息
 */
public class LoginUser {
    //用户id
    private String userId;
    //用户名
    private String username;
    //昵称
    private String nickname;
    //生日
    private String birthday;
    //性别
    private boolean sex;
    //头像
    private String avatarhead;

    public LoginUser() {
    }
    public String getAvatarhead() {
        return avatarhead;
    }

    public void setAvatarhead(String avatarhead) {
        this.avatarhead = avatarhead;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "LoginUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", avatarhead='" + avatarhead + '\'' +
                '}';
    }
}
