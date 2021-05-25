package ch.joelproko.distanzen_per_oev;

import java.util.Date;
import java.util.Objects;

public class DepartureData implements Comparable<DepartureData> {
    public String srcAddr;
    public String srcTime;
    public String arrTime;
    public Long date;

    @Override
    public int compareTo(DepartureData o) {
        return date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartureData that = (DepartureData) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
