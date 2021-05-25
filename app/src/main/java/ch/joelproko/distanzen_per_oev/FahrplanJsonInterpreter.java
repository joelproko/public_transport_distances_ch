package ch.joelproko.distanzen_per_oev;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class FahrplanJsonInterpreter {
    private List<Destination> destinations;
    private MutableLiveData<TreeSet<DepartureData>> departureDataList;
    private LinkedList<Long> exactDateTimes;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static Long maxDiff = 24*60*60*1000L;
    public FahrplanJsonInterpreter(List<Destination> destinationList, MutableLiveData<TreeSet<DepartureData>> departureData) {
        destinations = destinationList;
        departureDataList = departureData;
        exactDateTimes = new LinkedList<>();
        destinations.forEach(destination -> {
            long date = destination.date;
            date += (Integer.parseInt(destination.time.substring(0,2)))*1000*60*60L;
            date += (Integer.parseInt(destination.time.substring(3)))*1000*60L;
            exactDateTimes.add(date);
        });
    }

    private class ComparedDate implements Comparable<ComparedDate> {
        public long date = 0;
        public int position = 0;
        public Long diff = null;
        public final int destinationIndex;
        public ComparedDate(int dest) {
            destinationIndex=dest;
        }

        @Override
        public int compareTo(ComparedDate o) {
            if (this.diff==null && o.diff==null) return 0;
            if (this.diff==null) return Integer.MAX_VALUE;
            if (o.diff==null) return -Integer.MAX_VALUE;
            return this.diff.compareTo(o.diff);
        }
    }

    public void interpret(String response) throws JSONException, ParseException {
        LinkedList<ComparedDate> closestDates = new LinkedList<>();
        for (int i=0;i<exactDateTimes.size();i++) {
            closestDates.add(new ComparedDate(i));
        };
        final JSONObject jobject = new JSONObject(response);
        final JSONArray connections = jobject.getJSONArray("connections");
        final int n = connections.length();
        for (int i=0;i<n;i++) {
            JSONObject connection = connections.getJSONObject(i);
            long arrival = df.parse(connection.getString("arrival")).getTime();
            for (int j=0;j<exactDateTimes.size();j++) {
                Long diff = exactDateTimes.get(j)-arrival;
                if (diff<0) continue;
                if (diff>maxDiff) continue;
                if (closestDates.get(j).diff==null || closestDates.get(j).diff>diff) {
                    closestDates.get(j).date=arrival;
                    closestDates.get(j).position = i;
                    closestDates.get(j).diff=diff;
                }
            }
        }
        Collections.sort(closestDates);
        JSONObject connection = connections.getJSONObject(closestDates.get(0).position);
        DepartureData d = new DepartureData();
        d.destAddr = connection.getString("to");
        d.srcTime = connection.getString("departure");
        d.arrTime = connection.getString("arrival");
        d.date = exactDateTimes.get(closestDates.get(0).destinationIndex);
        synchronized (departureDataList) {
            TreeSet<DepartureData> currentList = departureDataList.getValue();
            currentList.add(d);
            departureDataList.postValue(currentList);
        }
    }
}
