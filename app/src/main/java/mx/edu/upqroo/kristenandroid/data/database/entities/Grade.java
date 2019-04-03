package mx.edu.upqroo.kristenandroid.data.database.entities;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "grade",
        indices = {@Index("userId")},
        foreignKeys = @ForeignKey(entity = UserInformation.class,
                parentColumns = "userId",
                childColumns = "userId",
                onDelete = CASCADE))
public class Grade {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gradeId")
    private long gradeId;

    @NonNull
    @SerializedName("grupo")
    @ColumnInfo(name = "code")
    private String code;

    @NonNull
    @SerializedName("nombre_mat")
    @ColumnInfo(name = "subject")
    private String subject;

    @NonNull
    @SerializedName("calificacion")
    @ColumnInfo(name = "generalGrade")
    private String generalGrade;

    @NonNull
    @SerializedName("parcial_1")
    @ColumnInfo(name = "gradeOne")
    private String gradeOne;

    @NonNull
    @SerializedName("parcial_2")
    @ColumnInfo(name = "gradeTwo")
    private String gradeTwo;

    @NonNull
    @SerializedName("parcial_3")
    @ColumnInfo(name = "gradeThree")
    private String gradeThree;

    @NonNull
    @SerializedName("parcial_4")
    @ColumnInfo(name = "gradeFour")
    private String gradeFour;

    @NonNull
    @SerializedName("parcial_5")
    @ColumnInfo(name = "gradeFive")
    private String gradeFive;

    @NonNull
    @SerializedName("matricula")
    @ColumnInfo(name = "userId")
    private String userId;

    public Grade(long gradeId,
                 @NonNull String code,
                 @NonNull String subject,
                 @NonNull String generalGrade,
                 @NonNull String gradeOne,
                 @NonNull String gradeTwo,
                 @NonNull String gradeThree,
                 @NonNull String gradeFour,
                 @NonNull String gradeFive,
                 @NonNull String userId) {
        this.gradeId = gradeId;
        this.code = code;
        this.subject = subject;
        this.generalGrade = generalGrade;
        this.gradeOne = gradeOne;
        this.gradeTwo = gradeTwo;
        this.gradeThree = gradeThree;
        this.gradeFour = gradeFour;
        this.gradeFive = gradeFive;
        this.userId = userId;
    }

    public long getGradeId() {
        return gradeId;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getGeneralGrade() {
        return generalGrade;
    }

    @NonNull
    public String getGradeOne() {
        return gradeOne;
    }

    @NonNull
    public String getGradeTwo() {
        return gradeTwo;
    }

    @NonNull
    public String getGradeThree() {
        return gradeThree;
    }

    @NonNull
    public String getGradeFour() {
        return gradeFour;
    }

    @NonNull
    public String getGradeFive() {
        return gradeFive;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }
}
