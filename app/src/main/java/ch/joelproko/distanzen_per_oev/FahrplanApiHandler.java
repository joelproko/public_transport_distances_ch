package ch.joelproko.distanzen_per_oev;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class FahrplanApiHandler extends ViewModel {
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private final static String apiURL = "https://timetable.search.ch/api/route.json?";
    private final static String apiFrom = "from";
    private final static String apiTo = "to";
    private final static String apiDate = "date";
    private final static String apiTime = "time";
    private final static String apiType = "time_type";
    private final static String apiTypeVal = "arrival";
    private MutableLiveData<TreeSet<DepartureData>> liveData;
    private RequestQueue queue;

    public FahrplanApiHandler(android.content.Context c) {
        queue = Volley.newRequestQueue(c);
    }

    public LiveData<TreeSet<DepartureData>> getLatestDepartureTimes(List<Destination> destinationList,String srcAddr)
    {
        liveData = new MutableLiveData<TreeSet<DepartureData>>();
        TreeSet<DepartureData> list = new TreeSet<>();
        liveData.setValue(list);
        FahrplanJsonInterpreter interpret = new FahrplanJsonInterpreter(destinationList,liveData);
        destinationList.forEach(destination -> {
            StringBuilder requestString = new StringBuilder(apiURL);
            requestString.append(apiFrom+"=");
            requestString.append(srcAddr);
            requestString.append("&"+apiTo+"=");
            requestString.append(destination.address);
            requestString.append("&"+apiDate+"=");
            Date d = new Date(destination.date);
            requestString.append(format.format(d));
            requestString.append("&"+apiTime+"=");
            requestString.append(destination.time);
            requestString.append("&"+apiType+"="+apiTypeVal);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                requestString.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {interpret.interpret(response);}
                        catch (Exception e){}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error);
                    }
                });
            queue.add(stringRequest);
        });
        return liveData;
    }
}
