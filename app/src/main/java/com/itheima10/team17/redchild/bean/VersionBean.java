package com.itheima10.team17.redchild.bean;

/**
 * Created by tao on 2016/6/18.
 */
public class VersionBean {

    private ErrEntity.ErrorBean error;
    /**
     * response : version
     * version : {"id":1,"versioncode":2,"versionname":"1.2","url":"/red.apk","desc":"这是最新的apk"}
     */
    private String              response;
    private VersionEntity version;

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

    public void setVersion(VersionEntity version) {
        this.version = version;
    }

    public String getResponse() {
        return response;
    }

    public VersionEntity getVersion() {
        return version;
    }

    public static class VersionEntity {
        /**
         * id : 1
         * versioncode : 2
         * versionname : 1.2
         * url : /red.apk
         * desc : 这是最新的apk
         */
        private int id;
        private int    versioncode;
        private String versionname;
        private String url;
        private String desc;

        public void setId(int id) {
            this.id = id;
        }

        public void setVersioncode(int versioncode) {
            this.versioncode = versioncode;
        }

        public void setVersionname(String versionname) {
            this.versionname = versionname;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public int getVersioncode() {
            return versioncode;
        }

        public String getVersionname() {
            return versionname;
        }

        public String getUrl() {
            return url;
        }

        public String getDesc() {
            return desc;
        }
    }
}
