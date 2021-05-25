package ch.joelproko.distanzen_per_oev;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DestinationDao {

    @Insert
    void insert(Destination destination);

    @Delete
    void delete(Destination destination);

    @Query("SELECT * FROM destinations ORDER BY date ASC")
    LiveData<List<Destination>> getAll();

    @Query("DELETE FROM destinations WHERE date = :dateId")
    void delete(Long dateId);

    @Query("SELECT * FROM destinations WHERE date = :dateId")
    LiveData<List<Destination>> getOne(Long dateId);

    @Update
    void update(Destination destination);
}
