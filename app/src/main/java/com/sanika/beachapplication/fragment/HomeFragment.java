// Request location and fetch beaches based on your location in Mumbai.
package com.sanika.beachapplication.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.adapter.BeachAdapter;
import com.sanika.beachapplication.api.ApiClient;
import com.sanika.beachapplication.api.ApiInterface;
import com.sanika.beachapplication.model.Beach;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private RecyclerView recyclerView;
    private BeachAdapter beachAdapter;
    private List<Beach> beachList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView and set layout manager
        recyclerView = view.findViewById(R.id.recyclerViewBeaches);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // Initialize list and adapter
        beachList = new ArrayList<>();
        beachAdapter = new BeachAdapter(requireActivity(), beachList);
        recyclerView.setAdapter(beachAdapter);

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Setup location request for high accuracy
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);  // High accuracy mode
        locationRequest.setInterval(10000);  // 10 seconds
        locationRequest.setFastestInterval(5000);  // 5 seconds

        // Location callback to get updated location
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.d("Exact Location", "Latitude: " + latitude + ", Longitude: " + longitude);
                    fetchNearbyBeaches(latitude, longitude);
                }
            }
        };

        // Request location and fetch data
        getLocationAndFetchData();

        return view;
    }

    // Request Location and Fetch Data
    private void getLocationAndFetchData() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Get last known location
        fusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    fetchNearbyBeaches(latitude, longitude);
                } else {
                    Toast.makeText(requireContext(), "Unable to get location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Handle Location Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationAndFetchData();
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Fetch Nearby Beaches
    private void fetchNearbyBeaches(double latitude, double longitude) {
        ApiInterface apiInterface = ApiClient.getApi();
        Call<List<Beach>> call = apiInterface.getNearbyBeaches("beach", "json", latitude, longitude, 5000);

        // Check if the correct coordinates are being passed
        Log.d("Location", "Latitude: " + latitude + ", Longitude: " + longitude);

        call.enqueue(new Callback<List<Beach>>() {
            @Override
            public void onResponse(Call<List<Beach>> call, Response<List<Beach>> response) {if (response.isSuccessful() && response.body() != null) {
                    beachList.clear();
                    beachList.addAll(response.body());
                    beachAdapter.notifyDataSetChanged();
                } else {
                    Log.e("Error", "Failed to fetch beaches. Response not successful.");
                    Toast.makeText(requireContext(), "Failed to fetch beaches", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Beach>> call, Throwable t) {
                Log.e("Error", "Failed to fetch beaches: " + t.getMessage());
                Toast.makeText(requireContext(), "Error fetching beaches: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
