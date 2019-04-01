package mx.edu.upqroo.kristenandroid.data.database.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notice")
public class Notice {

    @PrimaryKey()
    @SerializedName("idAvisos")
    @ColumnInfo(name = "noticeId")
    @NonNull
    private String noticeId;

    @SerializedName("titulo")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("cuerpo")
    @ColumnInfo(name = "body")
    private String body;

    @SerializedName("fecha")
    @ColumnInfo(name = "date")
    private Date date;

    @SerializedName("idUsuarios")
    @ColumnInfo(name = "userId")
    private String userId;

    @SerializedName("idCarrera")
    @ColumnInfo(name = "careerId")
    private int careerId;

    public Notice(@NonNull String noticeId,
                  String title,
                  String body,
                  Date date,
                  String userId,
                  int careerId) {
        this.noticeId = noticeId;
        this.title = title;
        this.body = body;
        this.date = date;
        this.userId = userId;
        this.careerId = careerId;
    }

    @NonNull
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(@NonNull String noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCareerId() {
        return careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }
}
