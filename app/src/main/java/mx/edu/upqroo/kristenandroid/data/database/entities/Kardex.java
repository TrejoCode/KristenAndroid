package mx.edu.upqroo.kristenandroid.data.database.entities;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "kardex",
        indices = {@Index("userId")},
        foreignKeys = @ForeignKey(entity = UserInformation.class,
                parentColumns = "userId",
                childColumns = "userId",
                onDelete = CASCADE))
public class Kardex {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kardexId")
    private long kardexId;

    @NonNull
    @SerializedName("nombre_mat")
    @ColumnInfo(name = "subject")
    private String subject;

    @NonNull
    @SerializedName("calificacion")
    @ColumnInfo(name = "grade")
    private String grade;

    @NonNull
    @SerializedName("cuatrimestre")
    @ColumnInfo(name = "quarter")
    private String quarter;

    @NonNull
    @SerializedName("matricula")
    @ColumnInfo(name = "userId")
    private String userId;

    public Kardex(long kardexId,
                @NotNull String subject,
                @NotNull String grade,
                @NotNull String quarter,
                @NotNull String userId) {
        this.kardexId = kardexId;
        this.subject = subject;
        this.grade = grade;
        this.quarter = quarter;
        this.userId = userId;
    }

    public long getKardexId() {
        return kardexId;
    }

    public void setKardexId(long kardexId) {
        this.kardexId = kardexId;
    }

    @NotNull
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String subject) {
        this.subject = subject;
    }

    @NotNull
    public String getGrade() {
        return grade;
    }

    public void setGrade(@NotNull String grade) {
        this.grade = grade;
    }

    @NotNull
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(@NotNull String quarter) {
        this.quarter = quarter;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }
}
