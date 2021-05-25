package ch.joelproko.distanzen_per_oev;

import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public abstract class DestinationDetails extends AppCompatActivity {
    public static final String EXTRA_REPLY_ID = "ch.joelproko.distanzen.R_ID";
    public static final String EXTRA_REPLY_ADDR = "ch.joelproko.distanzen.RADDR";
    public static final String EXTRA_REPLY_DATE = "ch.joelproko.distanzen.RDATE";
    public static final String EXTRA_REPLY_TIME = "ch.joelproko.distanzen.RTIME";

    protected EditText mEditAddressView;
    protected CalendarView mCalendarView;
    protected TimePicker mTimePickerView;
}
