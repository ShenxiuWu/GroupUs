package com.github.GroupUs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import com.sun.deploy.util.StringUtils;

/*
 *  The Google Maps Geolocation API returns a location and accuracy radius based on information
 *  about cell towers and WiFi nodes that the mobile client can detect.
 *
 * <p>Please see the<a href="https://developers.google.com/maps/documentation/geolocation/intro#top_of_page">
 *   Geolocation API</a> for more detail.
 *
 *
 */
public class distance {
    public static void main(String[] args) {
        geoCoding();
        distanceMatirx();
    }
    private static void geoCoding() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAGqEp56siF_9xsaDcxB5JTIczUOb1s7dc")
                .build();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context,
                    "Columbia University").await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(results[0].addressComponents));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void distanceMatirx() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAGqEp56siF_9xsaDcxB5JTIczUOb1s7dc")
                .build();
        try {
            String[] origins = new String[] {"Columbia University NY"};
            String[] destinations = new String[] {"Flushing NY"};
            DistanceMatrix result = DistanceMatrixApi.newRequest(context)
                    .origins(origins)
                    .destinations(destinations)
                    .mode(TravelMode.TRANSIT)
                    .language("fr-FR")
                    .await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}