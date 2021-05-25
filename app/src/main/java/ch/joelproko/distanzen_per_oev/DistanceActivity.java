package ch.joelproko.distanzen_per_oev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DistanceActivity extends AppCompatActivity {

    private DestinationViewModel mDestinationViewModel;
    private DestinationViewModel mDistanceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distanzen_anzeige);


        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_distance);
        final DestinationListAdapter adapter = new DestinationListAdapter(new DestinationListAdapter.DestinationDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDestinationViewModel = new ViewModelProvider(this).get(DestinationViewModel.class);
        mDestinationViewModel.getAllDestinations().observe(this, destinations -> {
            if (destinations.size()>0) {
                if (Intent.ACTION_SEND.equals(action) && type != null) {
                    if ("text/plain".equals(type)) {
                        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                        handleSendText(sharedText, destinations); // Handle text being sent
                    }
                } else {
                    handleSendText("Zürich, Museumstr. 1", destinations);
                }
            }
        });


    }

    private void handleSendText(String sharedText,List<Destination> destinations) {
        if (sharedText != null) {
            TextView t = findViewById(R.id.srcAddrView);
            t.setText(sharedText);
            final DistanceListAdapter adapter = new DistanceListAdapter(new DistanceListAdapter.DepartureDiff());
            mDistanceViewModel = new ViewModelProvider(this).get(DestinationViewModel.class);
            mDistanceViewModel.getDepartureTimes(sharedText,destinations).observe(this, departureData -> {
                adapter.submitList(new ArrayList<DepartureData>(departureData));
            });
        }
    }


}