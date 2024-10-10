package com.sanika.beachapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.activity.BeachDetailsActivity;
import com.sanika.beachapplication.model.Beach;
import com.sanika.beachapplication.model.BeachFeature;
import com.sanika.beachapplication.model.BeachProperties;

import java.util.List;

public class BeachAdapter extends RecyclerView.Adapter<BeachAdapter.BeachViewHolder> {
    private Context context;
    private List<BeachFeature> beachList;

    public BeachAdapter(Context context, List<BeachFeature> beachList) {
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
        BeachFeature beach = beachList.get(position);
        BeachProperties beachProperties = beach.getProperties();
        holder.textViewBeachName.setText(beachProperties.getName());
        holder.textViewBeachDescription.setText(beachProperties.getName());

        // Load beach image using Glide
        Glide.with(context)
                .load(beachProperties.getName())  // Ensure this is the correct URL for the image
                .placeholder(R.drawable.img3) // Optional: Placeholder image while loading
                .into(holder.imageViewBeachImage);
        // Set item click listener
        holder.imageView2.setOnClickListener(v -> {
            Intent intent = new Intent(context, BeachDetailsActivity.class);
            intent.putExtra("BEACH_NAME", beachProperties.getName());
            intent.putExtra("BEACH_LATITUDE", beach.getGeometry().getCoordinates().get(1)); // Latitude
            intent.putExtra("BEACH_LONGITUDE", beach.getGeometry().getCoordinates().get(0)); // Longitude
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return beachList.size();
    }

    public static class BeachViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBeachImage;
        ImageView imageView2;
        TextView textViewBeachName;
        TextView textViewBeachDescription;

        public BeachViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBeachImage = itemView.findViewById(R.id.imageViewBeachImage);
            imageView2 = itemView.findViewById(R.id.imageView2);
            textViewBeachName = itemView.findViewById(R.id.textViewBeachName);
            textViewBeachDescription = itemView.findViewById(R.id.country_name);
        }
    }
}
