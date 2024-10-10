package com.sanika.beachapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.model.HotelDetail;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private Context context;
    private List<HotelDetail> hotelDetails;

    public HotelAdapter(Context context, List<HotelDetail> hotelDetails) {
        this.context = context;
        this.hotelDetails = hotelDetails;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelDetail hotel = hotelDetails.get(position);

        holder.textViewHotelName.setText(hotel.getName());
        holder.textViewHotelDescription.setText(hotel.getDescription());
        holder.textViewHotelPhone.setText(hotel.getFormattedPhoneNumber());
// Set an OnClickListener on the phone TextView
        holder.textViewHotelPhone.setOnClickListener(v -> {
            // Create an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Contact Hotel")
                    .setMessage("Phone: " + hotel.getFormattedPhoneNumber()) // Display the phone number
                    .setPositiveButton("Call", (dialog, which) -> {
                        // Create an intent to dial the number
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + hotel.getFormattedPhoneNumber()));
                        context.startActivity(intent); // Start the dialer
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {
                        dialog.dismiss(); // Dismiss the dialog if Cancel is clicked
                    });

            // Show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        // Load the hotel image using Glide
        if (hotel.getPhotos() != null && !hotel.getPhotos().isEmpty()) {
            String imageUrl = hotel.getPhotos().get(0).getPhotoReference(); // Use your API Key here
            Glide.with(context).load(imageUrl).into(holder.imageViewHotel);
        }
    }

    @Override
    public int getItemCount() {
        return hotelDetails.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHotelName;
        TextView textViewHotelDescription;
        TextView textViewHotelPhone;
        ImageView imageViewHotel;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHotelName = itemView.findViewById(R.id.place_name);
            textViewHotelDescription = itemView.findViewById(R.id.country_name);
            textViewHotelPhone = itemView.findViewById(R.id.price);
            imageViewHotel = itemView.findViewById(R.id.place_image);
        }
    }
}
