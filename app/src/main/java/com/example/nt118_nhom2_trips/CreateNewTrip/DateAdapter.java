package com.example.nt118_nhom2_trips.CreateNewTrip;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.R;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder>{

    private Context mContext;
    private List<Date> mListDate;
    private OnItemClickListener listener;


    public DateAdapter(List<Date> mListDate, OnItemClickListener listener) {
        this.mListDate = mListDate;
        this.listener = listener;
    }

    public void setData(List<Date> list) {
        this.mListDate = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date,parent,false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        holder.bindData(mListDate.get(position));
    }

    @Override
    public int getItemCount() {
        if (mListDate != null) {
            return  mListDate.size();
        }
        return 0;
    }

    public class DateViewHolder extends RecyclerView.ViewHolder{
        private Date date;
        private TextView tv_day;
        private TextView tv_date;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(date);
                }
            });
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
        private void bindData(Date date) {
            this.date = date;
            tv_date.setText(date.getDate());
            tv_day.setText(date.getDay());
        }
    }
}
