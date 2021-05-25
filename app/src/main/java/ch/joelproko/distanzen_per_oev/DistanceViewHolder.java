package ch.joelproko.distanzen_per_oev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DistanceViewHolder extends RecyclerView.ViewHolder {

    private final TextView srcAddrView;
    private final TextView srcTimeView;
    private final TextView arrTimeView;

    private DistanceViewHolder(View itemView) {
        super(itemView);
        srcAddrView = itemView.findViewById(R.id.distanceSrcAddr);
        srcTimeView = itemView.findViewById(R.id.distanceSrcTime);
        arrTimeView = itemView.findViewById(R.id.distanceArrTime);
    }

    public void bind(String srcAddress, String srcDateTime, String arrDateTime) {
        srcAddrView.setText(srcAddress);
        srcTimeView.setText(srcDateTime);
        arrTimeView.setText(arrDateTime);
    }

    static DistanceViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_distance_item, parent, false);
        return new DistanceViewHolder(view);
    }
}
