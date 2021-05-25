package ch.joelproko.distanzen_per_oev;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DestinationListAdapter  extends ListAdapter<Destination, DestinationViewHolder> {

    private SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd.MM.yyyy");
    public DestinationListAdapter(@NonNull DiffUtil.ItemCallback<Destination> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DestinationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DestinationViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DestinationViewHolder holder, int position) {
        Destination current = getItem(position);
        Long dateLong = current.date;
        Date date = new Date(dateLong);
        holder.bind(current.address,formatter.format(date),current.time,dateLong);
    }

    static class DestinationDiff extends DiffUtil.ItemCallback<Destination> {

        @Override
        public boolean areItemsTheSame(@NonNull Destination oldItem, @NonNull Destination newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Destination oldItem, @NonNull Destination newItem) {
            return oldItem.address.equals(newItem.address) && oldItem.date.equals(newItem.date);
        }
    }

}
