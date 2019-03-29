package mx.edu.upqroo.kristenandroid.data.database.entities;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "day",
        indices = {@Index("userId")},
        foreignKeys = @ForeignKey(entity = UserInformation.class,
                parentColumns = "userId",
                childColumns = "userId",
                onDelete = CASCADE))
public class Day {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dayId")
    private long dayId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "userId")
    private String userId;

    public Day(long dayId, @NonNull String name, @NonNull String userId) {
        this.dayId = dayId;
        this.name = name;
        this.userId = userId;
    }

    public long getDayId() { return dayId; }

    @NotNull
    public String getName() {
        return name;
    }

    public String getUserId() { return userId; }
}
