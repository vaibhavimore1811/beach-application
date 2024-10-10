package com.sanika.beachapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanika.beachapplication.R;
import com.sanika.beachapplication.adapter.HotelAdapter;
import com.sanika.beachapplication.api.ApiClient;
import com.sanika.beachapplication.api.ApiInterface;
import com.sanika.beachapplication.model.Hotel;
import com.sanika.beachapplication.model.HotelDetail;
import com.sanika.beachapplication.model.HotelDetailResponse;
import com.sanika.beachapplication.model.HotelResponse;
import com.sanika.beachapplication.model.HotelSampleData;
import com.sanika.beachapplication.model.Main;
import com.sanika.beachapplication.model.Place;
import com.sanika.beachapplication.model.Weather;
import com.sanika.beachapplication.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeachDetailsActivity extends AppCompatActivity {
    private TextView textViewBeachName, textViewTemperature, textViewWeatherDescription, textViewHotelDetails;
    private RecyclerView recyclerViewHotels;

    private List<HotelDetail> hotelDetails;
    ImageView imgtemp;
    ImageView imageView4;
    private HotelAdapter hotelAdapter; // Assuming you have a HotelAdapter to display hotels
    //private List<Hotel> hotelList; // Assuming you have a Hotel class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beach_details);
        textViewBeachName = findViewById(R.id.textViewBeachName);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewWeatherDescription = findViewById(R.id.textViewWeatherDescription);
        recyclerViewHotels = findViewById(R.id.recyclerViewHotels);
        imgtemp = findViewById(R.id.imgtemp);
        imageView4 = findViewById(R.id.imageView4);

        hotelDetails = new ArrayList<>();
        hotelDetails = HotelSampleData.getSampleHotelDetails();
      //  hotelAdapter.notifyDataSetChanged();
        hotelAdapter = new HotelAdapter(this, hotelDetails);

        recyclerViewHotels.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewHotels.setAdapter(hotelAdapter);

        // Get beach details from intent
        Intent intent = getIntent();
        String beachName = intent.getStringExtra("BEACH_NAME");
        double beachLatitude = intent.getDoubleExtra("BEACH_LATITUDE", 0);
        double beachLongitude = intent.getDoubleExtra("BEACH_LONGITUDE", 0);

        textViewBeachName.setText(beachName);

        // Fetch hotels and weather
      //  fetchNearbyHotels(beachLatitude, beachLongitude);
        fetchWeatherData(beachLatitude, beachLongitude);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private void fetchNearbyHotels2(double latitude, double longitude) {
//        String apiKey = "YOUR_HOTEL_API_KEY"; // Replace with your hotel API key
//        String url = String.format("YOUR_HOTEL_API_URL?lat=%s&lon=%s&apikey=%s", latitude, longitude, apiKey);
//
//        ApiInterface apiInterface = ApiClient.getApi();
//        Call<HotelResponse> call = apiInterface.getNearbyHotels(url);
//
//        call.enqueue(new Callback<HotelResponse>() {
//            @Override
//            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    hotelList.clear();
//                    hotelList.addAll(response.body().getHotels());
//                    hotelAdapter.notifyDataSetChanged();
//                } else {
//                    Log.e("Error", "Failed to fetch hotels.");
//                    Toast.makeText(BeachDetailsActivity.this, "Failed to fetch hotels", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HotelResponse> call, Throwable t) {
//                Log.e("Error", "Failed to fetch hotels: " + t.getMessage());
//                Toast.makeText(BeachDetailsActivity.this, "Error fetching hotels: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void fetchNearbyHotels(double beachLatitude, double beachLongitude) {
        // Using provided beach latitude and longitude for nearby hotel search
//        double latitude = beachLatitude;
//        double longitude = beachLongitude;
        double latitude = 19.102652; // Mumbai Latitude
        double longitude = 72.824679; // Mumbai Longitude
        String apiKey = "AIzaSyBa2ns1JyOmwVpz2r2KF_BOGGDmJZ8Op2Q"; // Google Places API key
        String mumbaiUrl = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=20000&type=lodging&key=%s",
                latitude, longitude, apiKey); // Increased radius to 20,000 meters

        ApiInterface apiInterface = ApiClient.getApi();
        Call<HotelResponse> call = apiInterface.getNearbyHotels(mumbaiUrl);

        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hotelDetails.clear();
                    hotelDetails.addAll(response.body().getResults());
                    if (hotelDetails.isEmpty()) {
                        Log.e("Error", "No hotels found within the specified area.");
                        Toast.makeText(BeachDetailsActivity.this, "No hotels found within the specified area.", Toast.LENGTH_SHORT).show();
                    } else {
                        hotelAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("Error", "Failed to fetch hotels from Mumbai: " + response.message());
                    Toast.makeText(BeachDetailsActivity.this, "Failed to fetch hotels: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                Log.e("Error", "Failed to fetch hotels from Mumbai: " + t.getMessage());
                Toast.makeText(BeachDetailsActivity.this, "Error fetching hotels: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        hotelDetails = HotelSampleData.getSampleHotelDetails();
        hotelAdapter.notifyDataSetChanged();
       // hotelDetails.add(new HotelDetail("","","",""));
    }


    private void fetchHotelDetails(String placeId) {
        String apiKey = "AIzaSyBa2ns1JyOmwVpz2r2KF_BOGGDmJZ8Op2Q"; // Replace with your actual API key

        ApiInterface apiInterface = ApiClient.getApi();
        Call<HotelDetailResponse> call = apiInterface.getHotelDetails(placeId, apiKey);

        call.enqueue(new Callback<HotelDetailResponse>() {
            @Override
            public void onResponse(Call<HotelDetailResponse> call, Response<HotelDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    HotelDetail hotelDetail = response.body().getResult();
                    // Handle the hotel detail (e.g., add to a list, update UI, etc.)
                    updateHotelDetailsUI(hotelDetail);
                } else {
                    Log.e("Error", "Failed to fetch hotel details.");
                }
            }

            @Override
            public void onFailure(Call<HotelDetailResponse> call, Throwable t) {
                Log.e("Error", "Failed to fetch hotel details: " + t.getMessage());
            }
        });
    }
    private void updateHotelDetailsUI(HotelDetail hotelDetail) {

    }
    private void fetchWeatherData(double latitude, double longitude) {
        String apiKey = "95df43604483425602859a34530c9fef"; // Replace with your OpenWeatherMap API key
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric", latitude, longitude, apiKey);

        ApiInterface apiInterface = ApiClient.getApi();
        Call<WeatherResponse> call = apiInterface.getWeatherData(url);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    updateWeatherUI(weatherResponse);
                } else {
                    Log.e("Error", "Failed to fetch weather data.");
                    Toast.makeText(BeachDetailsActivity.this, "Failed to fetch weather", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Error", "Failed to fetch weather: " + t.getMessage());
                Toast.makeText(BeachDetailsActivity.this, "Error fetching weather: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateWeatherUI(WeatherResponse weatherResponse) {
        List<Weather> weather = weatherResponse.getWeather();
        Main main = weatherResponse.getMain();
        String weatherDescription = weather.get(0).getDescription().toLowerCase();
        double temperature = main.getTemperature();

        // Update UI
        textViewTemperature.setText(String.format("%.1f °C", temperature));
        textViewWeatherDescription.setText(weatherDescription);

        // Set appropriate icon and alert
        setWeatherIconAndAlert(weatherDescription, temperature);
    }
    private void setWeatherIconAndAlert(String weatherDescription, double temperature) {
        int weatherIconResourceId = R.drawable.sun; // Default icon for sunny weather
        boolean showAlert = false;

        // Check various weather conditions
        if (weatherDescription.contains("heavy rain")) {
            weatherIconResourceId = R.drawable.heavy_rain; // Icon for heavy rain
            showAlert = temperature > 30; // Alert if it's hot and raining heavily
        } else if (weatherDescription.contains("thunderstorm")) {
            weatherIconResourceId = R.drawable.thunderstorm; // Icon for thunderstorms
            showAlert = true; // Always alert for thunderstorms
        } else if (weatherDescription.contains("clear") || weatherDescription.contains("sunny")) {
            weatherIconResourceId = R.drawable.sun; // Icon for clear or sunny weather
            showAlert = temperature > 40; // Alert for extremely high temperatures
        } else if (weatherDescription.contains("cloud")) {
            weatherIconResourceId = R.drawable.cloudy; // Icon for cloudy weather
        } else if (weatherDescription.contains("hot")) {
            weatherIconResourceId = R.drawable.hot; // Icon for hot weather
            showAlert = temperature > 35; // Alert for extreme heat
        } else if (weatherDescription.contains("snow")) {
            weatherIconResourceId = R.drawable.snow; // Icon for snowy weather
            showAlert = true; // Always alert for snow
        } else if (weatherDescription.contains("fog")) {
            weatherIconResourceId = R.drawable.fog; // Icon for foggy weather
            showAlert = true; // Always alert for fog
        } else if (weatherDescription.contains("mist")) {
            weatherIconResourceId = R.drawable.mist; // Icon for misty weather
        } else if (weatherDescription.contains("hail")) {
            weatherIconResourceId = R.drawable.hail; // Icon for hail
            showAlert = true; // Always alert for hail
        } else if (weatherDescription.contains("windy") || weatherDescription.contains("storm")) {
            weatherIconResourceId = R.drawable.wind; // Icon for windy or stormy weather
            showAlert = true; // Always alert for storms and strong winds
        } else if (weatherDescription.contains("drizzle")) {
            weatherIconResourceId = R.drawable.drizzle; // Icon for light rain or drizzle
        } else if (weatherDescription.contains("sleet")) {
            weatherIconResourceId = R.drawable.sleet; // Icon for sleet
            showAlert = true; // Always alert for sleet
        } else if (weatherDescription.contains("cold")) {
            weatherIconResourceId = R.drawable.cold; // Icon for cold weather
            showAlert = temperature < 0; // Alert if it's freezing
        } else if (weatherDescription.contains("haze")) {
            weatherIconResourceId = R.drawable.haze; // Icon for haze
            showAlert = true; // Always alert for haze due to poor visibility and air quality
        }

        // Update the weather icon in the UI
        imgtemp.setImageResource(weatherIconResourceId);

        // Show weather alert if conditions are met
        if (showAlert) {
            showWeatherAlert(); // Call the alert method to display warnings
        }
    }

    private void showWeatherAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Weather Alert")
                .setMessage("It's currently heavy rain and hot outside. Stay safe!")
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}