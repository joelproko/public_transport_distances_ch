package ch.joelproko.distanzen_per_oev;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class DestinationRepository {
    private DestinationDao mDestinationDao;
    private LiveData<List<Destination>> mAllDestinations;
    private FahrplanApiHandler mFahrplanApiHandler;

    // Note that in order to unit test the DestinationRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    DestinationRepository(Application application) {
        DestinationRoomDatabase db = DestinationRoomDatabase.getDatabase(application);
        mDestinationDao = db.destinationDao();
        mAllDestinations = mDestinationDao.getAll();
        mFahrplanApiHandler = new FahrplanApiHandler(application);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Destination>> getAllDestinations() {
        return mAllDestinations;
    }
    LiveData<List<Destination>> getOneDestination(Long dateId) { return mDestinationDao.getOne(dateId);}
    LiveData<TreeSet<DepartureData>> getDepartureTimes(String srcAddr, List<Destination> destinations) { return mFahrplanApiHandler.getLatestDepartureTimes(destinations,srcAddr); }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Destination destination) {
        DestinationRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDestinationDao.insert(destination);
        });
    }

    void delete(Destination destination) {
        DestinationRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDestinationDao.delete(destination);
        });
    }

    void delete(Long dateId) {
        DestinationRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDestinationDao.delete(dateId);
        });
    }

    void update(Destination destination) {
        DestinationRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDestinationDao.update(destination);
        });
    }
}
