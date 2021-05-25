package ch.joelproko.distanzen_per_oev;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "destinations")
public class Destination {

    @NonNull
    public String address;

    @PrimaryKey
    @NonNull
    public Long date;

    @NonNull
    public String time;
}
