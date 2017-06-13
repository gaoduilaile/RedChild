package com.itheima10.team17.redchild.bean;

/**
 * Created by tao on 2016/6/20.
 */
public class Waiter {
    private int avatar;
    private String nickname;
    private int background;
    private String number;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    public Waiter(int avatar, String nickname, int background, String number, String desc) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.background = background;
        this.number = number;
        this.desc = desc;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public int getBackground() {
        return background;
    }

    public String getNumber() {
        return number;
    }
}
