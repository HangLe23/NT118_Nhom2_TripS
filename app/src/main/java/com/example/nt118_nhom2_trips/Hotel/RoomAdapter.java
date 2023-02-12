package com.example.nt118_nhom2_trips.Hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nt118_nhom2_trips.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context mContext;
    private List<Room> rooms;
    private OnRoomItemClickListener listener;

    public RoomAdapter(List<Room> rooms,OnRoomItemClickListener listener) {
        this.rooms = rooms;
        this.listener = listener;
    }

    public void setData(List<Room> list) {
        this.rooms = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.bindData(rooms.get(position));
    }

    @Override
    public int getItemCount() {
        if(rooms != null)
            return rooms.size();
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nameroom, tv_guest, tv_price, tv_sale;
        private ImageView img_room;
        private Room room;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onHotelItemClick(room);
                }
            });
            tv_nameroom = itemView.findViewById(R.id.tv_name_room);
            tv_guest = itemView.findViewById(R.id.tv_guest);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_sale = itemView.findViewById(R.id.tv_sale);
            img_room = itemView.findViewById(R.id.img_room);
        }

        private void bindData(Room room) {
            this.room = room;
            tv_nameroom.setText(room.getRoom_name());
            tv_guest.setText("Giá cho " + room.getGuest() + " người lớn");
            int price_room = room.getPrice() - room.getPrice()*room.getSale()/100;
            tv_price.setText(price_room + "");
            if (room.getSale() == 0){
                tv_sale.setVisibility(View.GONE);
            }
            String url = "";
            url = room.getImg_room();
            Picasso.get().load(url).into(img_room);
        }
    }
}
