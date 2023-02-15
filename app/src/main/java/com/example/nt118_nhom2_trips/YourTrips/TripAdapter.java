package com.example.nt118_nhom2_trips.YourTrips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.CreateNewTrip.Trips;
import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.Hotel.OnHotelItemClickListener;
import com.example.nt118_nhom2_trips.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder>{

    private Context mContext;
    private List<Trips> trips;
    private OnTripItemClickListener listener;

    public TripAdapter(List<Trips> trips, OnTripItemClickListener listener) {
        this.trips = trips;
        this.listener = listener;
    }

    public void setData(List<Trips> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trips, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        holder.bindData(trips.get(position));
    }

    @Override
    public int getItemCount() {
        if(trips != null)
            return trips.size();
        return 0;
    }

    public class TripViewHolder extends RecyclerView.ViewHolder {

        private TextView name, place, day;
        private Trips trip;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTripItemClick(trip);
                }
            });
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            day = itemView.findViewById(R.id.day);
        }
        private void bindData(Trips trip) {
            this.trip = trip;
            name.setText(trip.getName_trip());
            place.setText(trip.getPlace_trip());
            day.setText(trip.getStart_date_trip() + " - " + trip.getEnd_date_trip());
        }
    }
}
