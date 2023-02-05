package com.example.nt118_nhom2_trips.CreateNewTrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.R;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>{
    private Context mContext;
    private List<Activity> mListActivity;

    public ActivityAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Activity> list) {
        this.mListActivity = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity,parent,false);
        return new ActivityAdapter.ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = mListActivity.get(position);
        if (activity == null) {
            return;
        }
        holder.tv_time.setText(activity.getTime());
        holder.tv_name.setText(activity.getName());
        holder.tv_detail.setText(activity.getDetail());

    }

    @Override
    public int getItemCount() {
        if (mListActivity != null) {
            return  mListActivity.size();
        }
        return 0;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_time;
        private TextView tv_name;
        private TextView tv_detail;
        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_detail = itemView.findViewById(R.id.tv_detail);
        }
    }
}
