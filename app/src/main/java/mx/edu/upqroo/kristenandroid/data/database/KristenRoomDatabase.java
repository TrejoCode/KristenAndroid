package mx.edu.upqroo.kristenandroid.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import mx.edu.upqroo.kristenandroid.data.database.daos.DayDao;
import mx.edu.upqroo.kristenandroid.data.database.daos.KardexDao;
import mx.edu.upqroo.kristenandroid.data.database.daos.NoticeDao;
import mx.edu.upqroo.kristenandroid.data.database.daos.SubjectDao;
import mx.edu.upqroo.kristenandroid.data.database.daos.UserInformationDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;

@Database(entities = {
            UserInformation.class,
            Day.class,
            Subject.class,
            Notice.class,
            Kardex.class
        },
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class KristenRoomDatabase extends RoomDatabase {

    public abstract UserInformationDao userInformationDao();
    public abstract DayDao dayDao();
    public abstract SubjectDao subjectDao();
    public abstract NoticeDao noticeDao();
    public abstract KardexDao kardexDao();

    private static volatile KristenRoomDatabase mInstance;

    public static KristenRoomDatabase getInstance(final Context context) {
        if (mInstance == null) {
            synchronized (KristenRoomDatabase.class) {
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        KristenRoomDatabase.class, "test_database")
                        .build();
            }
        }
        return mInstance;
    }
}
