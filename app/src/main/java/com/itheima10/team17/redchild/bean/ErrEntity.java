package com.itheima10.team17.redchild.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Destiny on 2016/6/15.
 */
public class ErrEntity implements Parcelable {

    /**
     * code : -4
     * msg : token已经过时，请重新登录！
     */

    public ErrorBean error;
    /**
     * error : {"code":-4,"msg":"token已经过时，请重新登录！"}
     * response : error
     */

    public String response;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class ErrorBean implements Parcelable {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.code);
            dest.writeString(this.msg);
        }

        public ErrorBean() {
        }

        protected ErrorBean(Parcel in) {
            this.code = in.readInt();
            this.msg = in.readString();
        }

        public static final Creator<ErrorBean> CREATOR = new Creator<ErrorBean>() {
            @Override
            public ErrorBean createFromParcel(Parcel source) {
                return new ErrorBean(source);
            }

            @Override
            public ErrorBean[] newArray(int size) {
                return new ErrorBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.error, flags);
        dest.writeString(this.response);
    }

    public ErrEntity() {
    }

    protected ErrEntity(Parcel in) {
        this.error = in.readParcelable(ErrorBean.class.getClassLoader());
        this.response = in.readString();
    }

    public static final Parcelable.Creator<ErrEntity> CREATOR = new Parcelable.Creator<ErrEntity>() {
        @Override
        public ErrEntity createFromParcel(Parcel source) {
            return new ErrEntity(source);
        }

        @Override
        public ErrEntity[] newArray(int size) {
            return new ErrEntity[size];
        }
    };
}
