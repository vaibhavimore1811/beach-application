package com.sanika.beachapplication.model;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelSampleData {
    public static List<HotelDetail> getSampleHotelDetails() {
        List<HotelDetail> hotelDetails = new ArrayList<>();

        // Sample Hotel 1
        HotelDetail hotel1 = new HotelDetail();
        hotel1.setName("Sea View Hotel");
        hotel1.setFormatted_phone_number("+91 22 1234 5678");
        hotel1.setDescription("A beautiful hotel with a sea view and excellent amenities.");

        Photo photo1 = new Photo();
        photo1.setPhoto_reference("https://cf.bstatic.com/xdata/images/hotel/max1024x768/367093926.jpg?k=52b58aca971a2a8df5ea0fa99426724aa0e54be323a3a68ad6ad54884cf0033d&o=&hp=1");
        hotel1.setPhotos(Arrays.asList(photo1)); // Assuming you have a method to set photos

        hotelDetails.add(hotel1);

        // Sample Hotel 2
        HotelDetail hotel2 = new HotelDetail();
        hotel2.setName("Mountain Retreat");
        hotel2.setFormatted_phone_number("+91 22 2345 6789");
        hotel2.setDescription("A cozy retreat located in the mountains.");

        Photo photo2 = new Photo();
        photo2.setPhoto_reference("https://cf.bstatic.com/xdata/images/hotel/max1024x768/367093926.jpg?k=52b58aca971a2a8df5ea0fa99426724aa0e54be323a3a68ad6ad54884cf0033d&o=&hp=1");
        hotel2.setPhotos(Arrays.asList(photo2)); // Assuming you have a method to set photos


        hotelDetails.add(hotel2);

        // Sample Hotel 3
        HotelDetail hotel3 = new HotelDetail();
        hotel3.setName("City Center Inn");
        hotel3.setFormatted_phone_number("+91 22 3456 7890");
        hotel3.setDescription("An affordable hotel in the heart of the city.");

        Photo photo3 = new Photo();
        photo3.setPhoto_reference("https://cf.bstatic.com/xdata/images/hotel/max1024x768/367093926.jpg?k=52b58aca971a2a8df5ea0fa99426724aa0e54be323a3a68ad6ad54884cf0033d&o=&hp=1");
        hotel3.setPhotos(Arrays.asList(photo3)); // Assuming you have a method to set photos

        hotelDetails.add(hotel3);

        return hotelDetails;
    }
}
