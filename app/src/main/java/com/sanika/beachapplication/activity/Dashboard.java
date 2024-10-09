package com.sanika.beachapplication.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.sanika.beachapplication.R;
import com.sanika.beachapplication.adapter.BeachAdapter;
import com.sanika.beachapplication.api.ApiClient;
import com.sanika.beachapplication.api.ApiInterface;
import com.sanika.beachapplication.model.Beach;
import com.sanika.beachapplication.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private RecyclerView recyclerView;
    private BeachAdapter beachAdapter;
    private List<Beach> beachList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerViewBeaches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        beachList = new ArrayList<>(); // Initialize the list
        beachAdapter = new BeachAdapter(this, beachList);
        recyclerView.setAdapter(beachAdapter);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationAndFetchData();
    }

    private void getLocationAndFetchData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        Task<Location> locationTask = fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    fetchNearbyBeaches(latitude, longitude);
                    fetchWeather(latitude, longitude);
                } else {
                    Toast.makeText(Dashboard.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchNearbyBeaches(double latitude, double longitude) {
        ApiInterface apiInterface = ApiClient.getApi();
        Call<List<Beach>> call = apiInterface.getNearbyBeaches("beach", "json", latitude, longitude, 5000);

        call.enqueue(new Callback<List<Beach>>() {
            @Override
            public void onResponse(Call<List<Beach>> call, Response<List<Beach>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Beach> beaches = response.body();

                    // Update the adapter
                    beachAdapter = new BeachAdapter(Dashboard.this, beaches);
                    recyclerView.setAdapter(beachAdapter);
                } else {
                    Log.e("Error", "Response was not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Beach>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void fetchWeather(double latitude, double longitude) {
        ApiInterface apiInterface = ApiClient.getApi();
        String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=YOUR_OPENWEATHERMAP_API_KEY";
        Call<WeatherResponse> call = apiInterface.getWeather(weatherUrl);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    Log.d("Weather", "Temperature: " + weatherResponse.getMain().getTemp());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationAndFetchData();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
