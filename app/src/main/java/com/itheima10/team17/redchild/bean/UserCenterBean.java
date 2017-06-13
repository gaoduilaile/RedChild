package com.itheima10.team17.redchild.bean;

/**
 * Created by tao on 2016/6/17.
 */
public class UserCenterBean {

    private ErrEntity.ErrorBean error;
    /**
     * userInfo : {"password":"123","level":"普通会员","user_id":"14599337416420","bonus":0,"orderCount":1,"id":49,"couponid":0,"favoritesCount":2,"token":1466153128886,"username":"123@123"}
     * response : response
     */
    private UserInfoEntity      userInfo;
    private String response;

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

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public String getResponse() {
        return response;
    }

    public static class UserInfoEntity {
        /**
         * password : 123
         * level : 普通会员
         * user_id : 14599337416420
         * bonus : 0
         * orderCount : 1
         * id : 49
         * couponid : 0
         * favoritesCount : 2
         * token : 1466153128886
         * username : 123@123
         */
        private String password;
        private String level;
        private String user_id;
        private int    bonus;
        private int    orderCount;
        private int    id;
        private int    couponid;
        private int    favoritesCount;
        private long   token;
        private String username;

        public void setPassword(String password) {
            this.password = password;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public void setToken(long token) {
            this.token = token;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public String getLevel() {
            return level;
        }

        public String getUser_id() {
            return user_id;
        }

        public int getBonus() {
            return bonus;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public int getId() {
            return id;
        }

        public int getCouponid() {
            return couponid;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public long getToken() {
            return token;
        }

        public String getUsername() {
            return username;
        }
    }
}
