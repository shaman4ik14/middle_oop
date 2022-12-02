package com.example.demo.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import lombok.SneakyThrows;

public class GooglePlaces {
    @SneakyThrows
    public String getLocation(String args) {
        String API_KEY = "AIzaSyC_Crw40cIx0TLnM-jYVTbFPwVQJVVBrzU";
        String query = args;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesRespose = new TextSearchRequest(context).query(query).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String new_args = args;
        if (placesRespose.results.length == 0 && args.contains(".com"))
        {
            new_args = args.substring(0,args.indexOf(".com"));
            query = new_args;
            context = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();
            placesRespose = new TextSearchRequest(context).query(query).await();
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        String address = placesRespose.results[0].formattedAddress;
        String placeId = placesRespose.results[0].placeId;

        PlaceDetails placeDetails = new PlaceDetailsRequest(context).placeId(placeId).await();
        context.shutdown();
        return address;
    }
}
