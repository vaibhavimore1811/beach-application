// Request location and fetch beaches based on your location in Mumbai.
package com.sanika.beachapplication.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.sanika.beachapplication.constance.ConstanceMethod;
import com.sanika.beachapplication.model.Beach;
import com.sanika.beachapplication.model.BeachFeature;
import com.sanika.beachapplication.model.BeachResponse;

import java.io.IOException;
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
    private List<BeachFeature> beachListmain;
    private List<BeachFeature> beachList;
    Dialog progressdialog;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView and set layout manager
        recyclerView = view.findViewById(R.id.recyclerViewBeaches);
        editText = view.findViewById(R.id.editText);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // Initialize list and adapter
        beachList = new ArrayList<>();
        beachListmain = new ArrayList<>();
        beachAdapter = new BeachAdapter(requireActivity(), beachList);
        recyclerView.setAdapter(beachAdapter);
        progressdialog = ConstanceMethod.ShowProgressDialog(requireActivity());
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
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (beachListmain.size() > 0) {
                    // Clear the current beachList before populating it with filtered results
                    beachList.clear();

                    // Check if the search string 's' is not null or empty
                    if (s != null) {
                        // Iterate over the main list
                        for (int i = 0; i < beachListmain.size(); i++) {
                            BeachFeature beach = beachListmain.get(i);
                            // Check if the beach name contains the search string (case insensitive)
                            if (beach.getProperties().getName() != null &&
                                    beach.getProperties().getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                beachList.add(beach); // Add matching beach to the filtered list
                            }
                        }
                    } else {
                        // If the search string is empty, you may want to copy all items from the main list
                        beachList.addAll(beachListmain);
                    }

                    // Notify the adapter that the data has changed
                    beachAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
    private void fetchNearbyBeaches(double latitud1e, double longitu1de) {
        progressdialog.show();
        double latitude = 19.0760; // Mumbai Latitude
        double longitude = 72.8777; // Mumbai Longitude
        String categories = "beach"; // Category for beaches
        String filter = "circle:" + longitude + "," + latitude + ",10000"; // 10 km radius
        String bias = "proximity:" + longitude + "," + latitude; // Bias search towards user's location
        int limit = 20; // Limit to 20 results
        String apiKey = "516a96358b224a52a3318d472455f147"; // Replace with your actual API key

        ApiInterface apiInterface = ApiClient.getApi();
        Call<BeachResponse> call = apiInterface.getNearbyBeaches(categories, filter, bias, limit, apiKey);

        // Log the full request URL
        String requestUrl = "https://api.geoapify.com/v2/places?categories=" + categories + "&filter=" + filter + "&bias=" + bias + "&limit=" + limit + "&apiKey=" + apiKey;
        Log.d("API Request", "Request URL: " + requestUrl);

        call.enqueue(new Callback<BeachResponse>() {
            @Override
            public void onResponse(Call<BeachResponse> call, Response<BeachResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getFeatures().isEmpty()) {
                        Log.e("Error", "No beaches found in the specified area.");
                    }
                    beachList.clear();
                    beachListmain.clear();
                    beachListmain.addAll(response.body().getFeatures());
                    beachList.addAll(response.body().getFeatures());
                    beachAdapter.notifyDataSetChanged();

                    // Log the received beach details
                    for (BeachFeature beach : beachList) {
                        Log.d("Beach Details", "Name: " + beach.getProperties().getName() + ", Address: " + beach.getProperties().getName());
                    }
                } else {
                    Log.e("Error", "Response not successful. Code: " + response.code() + ", Message: " + response.message());
                    try {
                        Log.e("Response", "Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<BeachResponse> call, Throwable t) {
                progressdialog.dismiss();
                Log.e("Error", "Failed to fetch beaches: " + t.getMessage());
                Toast.makeText(requireContext(), "Error fetching beaches: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
