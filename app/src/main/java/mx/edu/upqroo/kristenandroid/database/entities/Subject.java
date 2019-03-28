package mx.edu.upqroo.kristenandroid.database.entities;

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
    @PrimaryKey
    @ColumnInfo(name = "subjectId")
    private int subjectId;

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
    private int dayId;

    public Subject(int subjectId, @NonNull String name, @NonNull String professor, @NonNull String time, int dayId) {
        this.subjectId = subjectId;
        this.name = name;
        this.professor = professor;
        this.time = time;
        this.dayId = dayId;
    }

    public int getSubjectId() {
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

    public int getDayId() {
        return dayId;
    }
}
