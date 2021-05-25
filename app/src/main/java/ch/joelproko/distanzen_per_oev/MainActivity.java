package ch.joelproko.distanzen_per_oev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private DestinationViewModel mDestinationViewModel;
    public static final int NEW_DESTINATION_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_DESTINATION_ACTIVITY_REQUEST_CODE = 2;

    public void deleteMe(View view) {
        String desc = (String) view.getContentDescription();
        Long date = Long.parseLong(desc);
        mDestinationViewModel.delete(date);
    }
    public void editMe(View view) {
        String desc = (String) view.getContentDescription();
        byte id = Byte.parseByte(desc.substring(11));
        ConstraintLayout cl = (ConstraintLayout) view.getParent();
        View test = cl.getViewById(3);
        Intent intent = new Intent(MainActivity.this, EditDestination.class);
        intent.putExtra(EditDestination.EXTRA_REPLY_ID,id);
        startActivityForResult(intent, EDIT_DESTINATION_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_main);
        final DestinationListAdapter adapter = new DestinationListAdapter(new DestinationListAdapter.DestinationDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDestinationViewModel = new ViewModelProvider(this).get(DestinationViewModel.class);
        mDestinationViewModel.getAllDestinations().observe(this, destinations -> {
            adapter.submitList(destinations);
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewDestination.class);
            startActivityForResult(intent, NEW_DESTINATION_ACTIVITY_REQUEST_CODE);
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_DESTINATION_ACTIVITY_REQUEST_CODE) {
            Destination destination = new Destination();
            destination.address = data.getStringExtra(NewDestination.EXTRA_REPLY_ADDR);
            destination.date = data.getLongExtra(NewDestination.EXTRA_REPLY_DATE,0);
            destination.time = data.getStringExtra(NewDestination.EXTRA_REPLY_TIME);
            mDestinationViewModel.insert(destination);
        }
        else if (requestCode == EDIT_DESTINATION_ACTIVITY_REQUEST_CODE) {
            Destination destination = new Destination();
            destination.address = data.getStringExtra(NewDestination.EXTRA_REPLY_ADDR);
            destination.date = data.getLongExtra(NewDestination.EXTRA_REPLY_DATE,0);
            destination.time = data.getStringExtra(NewDestination.EXTRA_REPLY_TIME);
            mDestinationViewModel.update(destination);
        }
    }

}