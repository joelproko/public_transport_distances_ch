package ch.joelproko.distanzen_per_oev;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Destination.class}, version = 1, exportSchema = false)
public abstract class DestinationRoomDatabase extends RoomDatabase {
    public abstract DestinationDao destinationDao();

    private static volatile DestinationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DestinationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DestinationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DestinationRoomDatabase.class, "distanzen_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
