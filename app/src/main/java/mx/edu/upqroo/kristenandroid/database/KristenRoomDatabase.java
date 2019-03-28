package mx.edu.upqroo.kristenandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import mx.edu.upqroo.kristenandroid.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.database.entities.UserInformationDao;

@Database(entities = {UserInformation.class}, version = 1)
public abstract class KristenRoomDatabase extends RoomDatabase {

    public abstract UserInformationDao userInformationDao();

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
