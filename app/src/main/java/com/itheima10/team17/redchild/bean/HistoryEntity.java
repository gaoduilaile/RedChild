package com.itheima10.team17.redchild.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Destiny on 2016/6/20.
 */
@Table("history")
public class HistoryEntity implements Parcelable {
    @PrimaryKey(AssignType.BY_MYSELF)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private long lastTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeLong(this.lastTime);
    }

    public HistoryEntity() {
    }

    protected HistoryEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.lastTime = in.readLong();
    }

    public static final Parcelable.Creator<HistoryEntity> CREATOR = new Parcelable.Creator<HistoryEntity>() {
        @Override
        public HistoryEntity createFromParcel(Parcel source) {
            return new HistoryEntity(source);
        }

        @Override
        public HistoryEntity[] newArray(int size) {
            return new HistoryEntity[size];
        }
    };
}
