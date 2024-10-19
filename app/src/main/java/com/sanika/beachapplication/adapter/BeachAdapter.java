package com.sanika.beachapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.activity.BeachDetailsActivity;
import com.sanika.beachapplication.model.BeachFeature;
import com.sanika.beachapplication.model.BeachProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeachAdapter extends RecyclerView.Adapter<BeachAdapter.BeachViewHolder> {
    private Context context;
    private List<BeachFeature> beachList; // Current list of beach features
    private List<BeachFeature> originalList; // Original list of beach features for filtering

    public BeachAdapter(Context context, List<BeachFeature> beachList) {
        this.context = context;
        this.beachList = beachList; // Initialize with a copy of the original list
        this.originalList = beachList; // Keep a separate copy of the original list
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

    @SuppressLint("NotifyDataSetChanged")
    public Filter filterStateTypeAndSort() {
        return beachFilter;
    }

    private Filter beachFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<BeachFeature> filteredResults = new ArrayList<>();

            // Check if the constraint is null or empty
            if (constraint == null || constraint.length() == 0) {
                // If no filter is applied, return the original list
                filteredResults.addAll(originalList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // Perform filtering using a traditional for loop
                for (BeachFeature beach : originalList) {
                    // Ensure that the field you're checking exists and is not null
                    if (beach.getProperties().getName() != null &&
                            beach.getProperties().getName().toLowerCase().contains(filterPattern)) {
                        filteredResults.add(beach);
                    }
                }
            }

            // Set the filtered results
            results.values = filteredResults;
            results.count = filteredResults.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Clear the current list
            beachList.clear();

            // Only add results if values is not null
            if (results.values != null) {
                beachList.addAll((Collection<? extends BeachFeature>) results.values);
            }

            // Notify that the data has changed
            notifyDataSetChanged();
        }
    };

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
