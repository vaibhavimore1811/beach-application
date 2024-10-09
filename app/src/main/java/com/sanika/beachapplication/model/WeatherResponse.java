package com.sanika.beachapplication.model;



public class WeatherResponse {
    private Main main;

    public Main getMain() {
        return main;
    }

    public static class Main {
        private double temp;

        public double getTemp() {
            return temp;
        }
    }
}
