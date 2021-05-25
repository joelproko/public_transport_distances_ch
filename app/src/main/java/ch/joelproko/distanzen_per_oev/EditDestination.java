package ch.joelproko.distanzen_per_oev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class EditDestination extends DestinationDetails {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_destination);
        mEditAddressView = findViewById(R.id.edit_destination);
        mCalendarView = findViewById(R.id.datePicker);
        mTimePickerView = findViewById(R.id.timePicker);
        mTimePickerView.setIs24HourView(true);
        Intent intent = getIntent();
        Long dateId = intent.getLongExtra(EXTRA_REPLY_DATE, 0L);
        DestinationViewModel mDestinationViewModel = new ViewModelProvider(this).get(DestinationViewModel.class);
        LiveData<List<Destination>> ldest = mDestinationViewModel.getOneDestination(dateId);
        Destination dest = ldest.getValue().get(0);
        mCalendarView.setDate(dest.date);
        mTimePickerView.setHour(Integer.parseInt(dest.time.substring(0,2)));
        mTimePickerView.setMinute(Integer.parseInt(dest.time.substring(3)));
        mEditAddressView.setText(dest.address);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditAddressView.getText())) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.toast_addressMissing,
                        Toast.LENGTH_LONG).show();
            } else {
                String address = mEditAddressView.getText().toString();
                Long mDate = mCalendarView.getDate();
                String mTime = mTimePickerView.getHour()+":"+mTimePickerView.getMinute();
                replyIntent.putExtra(EXTRA_REPLY_ID,dateId);
                replyIntent.putExtra(EXTRA_REPLY_ADDR, address);
                replyIntent.putExtra(EXTRA_REPLY_DATE,mDate);
                replyIntent.putExtra(EXTRA_REPLY_TIME,mTime);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}