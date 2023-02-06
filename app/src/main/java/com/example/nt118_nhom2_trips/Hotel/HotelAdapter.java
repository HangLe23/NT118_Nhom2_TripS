package com.example.nt118_nhom2_trips.Hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nt118_nhom2_trips.CreateNewTrip.Date;
import com.example.nt118_nhom2_trips.CreateNewTrip.OnItemClickListener;
import com.example.nt118_nhom2_trips.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder>{

    private Context mContext;
    private List<Hotel> hotels;
    private OnHotelItemClickListener listener;

    public HotelAdapter(List<Hotel> hotels,OnHotelItemClickListener listener) {
        this.hotels = hotels;
        this.listener = listener;
    }

    public void setData(List<Hotel> list) {
        this.hotels = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.bindData(hotels.get(position));
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
        private Hotel hotel;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onHotelItemClick(hotel);
                }
            });
            imghotel = itemView.findViewById(R.id.img_hotel);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
        }

        private void bindData(Hotel hotel) {
            this.hotel = hotel;
            name.setText(hotel.getName());
            address.setText(hotel.getAddress());

            String url = "";
            url = hotel.getImageUrl();
            Picasso.get().load(url).into(imghotel);
        }
    }
}
