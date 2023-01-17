package com.example.nt118_nhom2_trips.Hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder>{

    private List<Hotel> hotels;

    public HotelAdapter(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        if (hotel == null)
            return;
        holder.imghotel.setImageResource(hotel.getImage());
        holder.name.setText(hotel.getName());
        holder.address.setText(hotel.getAddress());
    }

    @Override
    public int getItemCount() {
        if(hotels != null)
            return hotels.size();
        return 0;
    }

    public  class  HotelViewHolder extends RecyclerView.ViewHolder{
        private ImageView imghotel;
        private TextView name;
        private TextView address;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            imghotel = itemView.findViewById(R.id.img_hotel);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
        }
    }
}
