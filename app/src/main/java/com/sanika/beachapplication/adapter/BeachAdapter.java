package com.sanika.beachapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.model.Beach;

import java.util.List;

public class BeachAdapter extends RecyclerView.Adapter<BeachAdapter.BeachViewHolder> {
    private Context context;
    private List<Beach> beachList;

    public BeachAdapter(Context context, List<Beach> beachList) {
        this.context = context;
        this.beachList = beachList;
    }

    @NonNull
    @Override
    public BeachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_beach, parent, false);
        return new BeachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeachViewHolder holder, int position) {
        Beach beach = beachList.get(position);
        holder.textViewBeachName.setText(beach.getDisplayName());
        holder.textViewBeachDescription.setText(beach.getDescription());

        // Load beach image using Glide
        Glide.with(context)
                .load(beach.getImageUrl())  // Ensure this is the correct URL for the image
                .placeholder(R.drawable.logo_beach) // Optional: Placeholder image while loading
                .into(holder.imageViewBeachImage);
    }

    @Override
    public int getItemCount() {
        return beachList.size();
    }

    public static class BeachViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBeachImage;
        TextView textViewBeachName;
        TextView textViewBeachDescription;

        public BeachViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBeachImage = itemView.findViewById(R.id.imageViewBeachImage);
            textViewBeachName = itemView.findViewById(R.id.textViewBeachName);
            textViewBeachDescription = itemView.findViewById(R.id.textViewBeachDescription);
        }
    }
}
