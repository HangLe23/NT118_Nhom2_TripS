package com.example.nt118_nhom2_trips.Tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.R;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.HotelViewHolder>{

    private List<Tour> tours;

    public TourAdapter(List<Tour> tours) {
        this.tours = tours;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Tour hotel = tours.get(position);
        if (hotel == null)
            return;
        holder.imgtour.setImageResource(hotel.getImage());
        holder.name.setText(hotel.getName());
        holder.address.setText(hotel.getAddress());
    }

    @Override
    public int getItemCount() {
        if(tours != null)
            return tours.size();
        return 0;
    }

    public  class  HotelViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgtour;
        private TextView name;
        private TextView address;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtour = itemView.findViewById(R.id.img_tour);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
        }
    }
}
