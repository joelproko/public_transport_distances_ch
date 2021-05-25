package ch.joelproko.distanzen_per_oev;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class DestinationViewModel extends AndroidViewModel {

    private DestinationRepository mRepository;

    private final LiveData<List<Destination>> mAllDestinations;

    public DestinationViewModel (Application application) {
        super(application);
        mRepository = new DestinationRepository(application);
        mAllDestinations = mRepository.getAllDestinations();
    }

    LiveData<List<Destination>> getAllDestinations() { return mAllDestinations; }
    LiveData<List<Destination>> getOneDestination(Long dateId) { return mRepository.getOneDestination(dateId); }
    LiveData<TreeSet<DepartureData>> getDepartureTimes(String srcAddr, List<Destination> destinations) { return  mRepository.getDepartureTimes(srcAddr,destinations);}

    public void insert(Destination destination) { mRepository.insert(destination); }
    public void delete(Destination destination) { mRepository.delete(destination);}
    public void delete(Long dateId) { mRepository.delete(dateId);}
    public void update(Destination destination) { mRepository.update(destination);};
}
