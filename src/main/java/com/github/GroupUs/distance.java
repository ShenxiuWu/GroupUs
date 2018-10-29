package com.github.GroupUs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static java.lang.Integer.parseInt;

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
        distanceMatirx(new String[] {"Stanford University"}, new String[] {"Columbia University"});
    }

    public static Integer distanceMatirx(String [] origins, String [] destinations) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBB2Mz7wexVz5mlpom9NqQc--6bf5tPbfo")
                .build();
        String res = "";
        Integer meters = 0;
        try {
            DistanceMatrix result = DistanceMatrixApi.newRequest(context)
                    .origins(origins)
                    .destinations(destinations)
                    .mode(TravelMode.TRANSIT)
                    .language("fr-FR")
                    .await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //System.out.println(gson.toJson(result));
            res = gson.toJson(result);
            JsonObject obj = gson.fromJson(res, JsonObject.class);
            //JSONParser parser = new JSONParser();
            //JSONObject obj = (JSONObject) parser.parse(res);
            //JSONObject obj = new JSONObject(result);
            JsonArray arr = obj.getAsJsonArray("rows");
            JsonArray arr1 = arr.get(0).getAsJsonObject().getAsJsonArray("elements");
            String str = gson.toJson(arr1.get(0).getAsJsonObject().get("distance").getAsJsonObject().get("inMeters"));
            meters = Integer.parseInt(str);
            //System.out.println(meters);
//            JSONObject obj2 = (JSONObject) obj1.get("distance");
//            obj2.get("inMeters");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meters;
    }
}