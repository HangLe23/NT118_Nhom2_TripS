package com.example.nt118_nhom2_trips.Tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.OnHotelItemClickListener;
import com.example.nt118_nhom2_trips.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.HotelViewHolder>{

    private List<Tour> tours;
    private OnTourItemClickListener listener;
    public TourAdapter(List<Tour> tours, OnTourItemClickListener listener) {
        this.tours = tours;
        this.listener = listener;
    }

    public void setData(List<Tour> list) {
        this.tours = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.bindData(tours.get(position));
    }

    @Override
    public int getItemCount() {
        if(tours != null)
            return tours.size();
        return 0;
    }

    public  void searchHotel(ArrayList<Tour> searchTour) {
        tours = searchTour;
        notifyDataSetChanged();
    }

    public  class  HotelViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgtour;
        private TextView name;
        private TextView day;
        private Tour tour;


        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTourItemClick(tour);
                }
            });
            imgtour = itemView.findViewById(R.id.img_tour);
            name = itemView.findViewById(R.id.name_tour);
            day = itemView.findViewById(R.id.day_tour);
        }
        private void bindData(Tour tour) {
            this.tour = tour;
            name.setText(tour.getName());
            day.setText(tour.getDay());

            String url = "";
            url = tour.getImage_tour();
            Picasso.get().load(url).into(imgtour);
        }
    }
}
