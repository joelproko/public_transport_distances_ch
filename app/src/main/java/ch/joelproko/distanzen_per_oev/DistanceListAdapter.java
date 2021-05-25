package ch.joelproko.distanzen_per_oev;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DistanceListAdapter  extends ListAdapter<DepartureData, DistanceViewHolder> {
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd.MM.yyyy");
    public DistanceListAdapter(@NonNull DiffUtil.ItemCallback<DepartureData> diffCallback) {
        super(diffCallback);
    }
    @Override
    public DistanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DistanceViewHolder.create(parent);
    }
    public void onBindViewHolder(DistanceViewHolder holder, int position) {
        DepartureData current = getItem(position);
        String srcDateTime = current.srcTime;
        String arrDateTime = current.arrTime;
        holder.bind(current.destAddr, srcDateTime,arrDateTime);
    }
    static class DepartureDiff extends DiffUtil.ItemCallback<DepartureData> {

        @Override
        public boolean areItemsTheSame(@NonNull DepartureData oldItem, @NonNull DepartureData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DepartureData oldItem, @NonNull DepartureData newItem) {
            return oldItem.srcTime.equals(newItem.srcTime) && oldItem.arrTime.equals(oldItem.arrTime) && oldItem.destAddr.equals(newItem.destAddr);
        }
    }
}
