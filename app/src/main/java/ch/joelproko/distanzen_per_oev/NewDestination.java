package ch.joelproko.distanzen_per_oev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewDestination extends DestinationDetails {

    public static int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_destination);
        mEditAddressView = findViewById(R.id.edit_destination);
        mCalendarView = findViewById(R.id.datePicker);
        mTimePickerView = findViewById(R.id.timePicker);
        mTimePickerView.setIs24HourView(true);
        Date today = new Date();
        mCalendarView.setDate(today.getTime());
        year = today.getYear();
        month = today.getMonth();
        day = today.getDay();
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                NewDestination.day = dayOfMonth;
                NewDestination.month = month;
                NewDestination.year = year-1900;
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }
        });

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
                Long mDate = new Date(year,month,day).getTime();
                String hour = "0"+mTimePickerView.getHour();
                if (hour.length()==3) hour = hour.substring(1);
                String mTime = hour+":"+mTimePickerView.getMinute();
                replyIntent.putExtra(EXTRA_REPLY_ADDR, address);
                replyIntent.putExtra(EXTRA_REPLY_DATE,mDate);
                replyIntent.putExtra(EXTRA_REPLY_TIME,mTime);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

    }
}