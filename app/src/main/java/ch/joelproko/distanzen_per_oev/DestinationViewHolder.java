package ch.joelproko.distanzen_per_oev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DestinationViewHolder extends RecyclerView.ViewHolder {
    public static final String EXTRA_MESSAGE_ID = "ch.joelproko.distanzen.ID";

    private final TextView addressItemView;
    private final TextView datetimeItemView;
    //private final ImageButton editItemView;
    private final ImageButton deleteItemView;

    private DestinationViewHolder(View itemView) {
        super(itemView);
        addressItemView = itemView.findViewById(R.id.textAddress);
        datetimeItemView = itemView.findViewById(R.id.textDateTime);
        //editItemView = itemView.findViewById(R.id.editButton);
        deleteItemView = itemView.findViewById(R.id.deleteButton);
    }

    public void bind(String address, String date, String time, Long dateId) {
        addressItemView.setText(address);
        datetimeItemView.setText(date+" "+time);
        //editItemView.setContentDescription("Edit Entry "+id);
        deleteItemView.setContentDescription("Delete Entry "+dateId);
    }

    static DestinationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_main_item, parent, false);
        return new DestinationViewHolder(view);
    }

}
