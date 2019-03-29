package mx.edu.upqroo.kristenandroid.data.database.entities;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "subject",
        indices = {@Index("dayId")},
        foreignKeys = @ForeignKey(entity = Day.class,
        parentColumns = "dayId",
        childColumns = "dayId",
        onDelete = CASCADE))
public class Subject {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subjectId")
    private long subjectId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "professor")
    private String professor;

    @NonNull
    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "dayId")
    private long dayId;

    public Subject(long subjectId,
                   @NonNull String name,
                   @NonNull String professor,
                   @NonNull String time,
                   long dayId) {
        this.subjectId = subjectId;
        this.name = name;
        this.professor = professor;
        this.time = time;
        this.dayId = dayId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getProfessor() {
        return professor;
    }

    @NotNull
    public String getTime() {
        return time;
    }

    public long getDayId() {
        return dayId;
    }
}
