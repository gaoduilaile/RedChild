package com.itheima10.team17.redchild.bean;

/**
 * Created by tao on 2016/6/15.
 * 登录后返回的用户数据,成功或失败信息,有response的值判断是否成功
 */
public class UserBean {

    /**
     * response : response
     * userInfo : {"id":43,"username":"xx","logState":false,"level":"白金会员","favoritesCount":0,"token":1465999125049,"couponid":0,"user_id":"114327","bonus":118,"password":"xx","orderCount":0}
     * error : {"code":-3,"msg":"用户不存在！"}
     */
    private String response;
    private UserInfoEntity userInfo;
    private ErrEntity.ErrorBean error;
    /**
     * error : {"code":-4,"msg":"token已经过时，请重新登录！"}
     * response : error
     */


    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }


    public void setResponse(String response) {
        this.response = response;
    }

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    public String getResponse() {
        return response;
    }

    public UserInfoEntity getUserInfo() {
        return userInfo;
    }


    public static class UserInfoEntity {
        /**
         * id : 43
         * username : xx
         * logState : false
         * level : 白金会员
         * favoritesCount : 0
         * token : 1465999125049
         * couponid : 0
         * user_id : 114327
         * bonus : 118
         * password : xx
         * orderCount : 0
         */
        private int id;
        private String  username;
        private boolean logState;
        private String  level;
        private int     favoritesCount;
        private long    token;
        private int     couponid;
        private String  user_id;
        private int     bonus;
        private String  password;
        private int     orderCount;

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setLogState(boolean logState) {
            this.logState = logState;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public void setToken(long token) {
            this.token = token;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public boolean isLogState() {
            return logState;
        }

        public String getLevel() {
            return level;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public long getToken() {
            return token;
        }

        public int getCouponid() {
            return couponid;
        }

        public String getUser_id() {
            return user_id;
        }

        public int getBonus() {
            return bonus;
        }

        public String getPassword() {
            return password;
        }

        public int getOrderCount() {
            return orderCount;
        }

        @Override
        public String toString() {
            return "UserInfoEntity{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", logState=" + logState +
                    ", level='" + level + '\'' +
                    ", favoritesCount=" + favoritesCount +
                    ", token=" + token +
                    ", couponid=" + couponid +
                    ", user_id='" + user_id + '\'' +
                    ", bonus=" + bonus +
                    ", password='" + password + '\'' +
                    ", orderCount=" + orderCount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "response='" + response + '\'' +
                ", userInfo=" + userInfo +
                ", error=" + error +
                '}';
    }
}
