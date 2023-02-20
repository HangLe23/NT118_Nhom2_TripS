package com.example.nt118_nhom2_trips.PlaceName;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.R;

import java.util.List;

public class PlaceNameAdapater extends RecyclerView.Adapter<PlaceNameAdapater.PlaceNameViewHolder>{

    private List<PlaceName> placeNames;
    private Context mContext;

    public PlaceNameAdapater(List<PlaceName> placeNames){
        this.placeNames = placeNames;
    }
    @NonNull
    @Override
    public PlaceNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diadanh, parent, false);
        return new PlaceNameViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(placeNames != null){
            return placeNames.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceNameViewHolder holder, int position) {
        PlaceName placeName = placeNames.get(position);
        if (placeName == null)
            return;
        holder.imgPlaceName.setImageResource(placeName.getId());
        holder.tvTivle.setText(placeName.getTitle());
    }

    public class PlaceNameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imgPlaceName;
        private TextView tvTivle;

        public PlaceNameViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlaceName = itemView.findViewById(R.id.img_diadanh);
            tvTivle = itemView.findViewById(R.id.tv_title);
        }
        @Override
        public void onClick(View view){
        }
    }
}
