package com.example.demo.parser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class PDLReader {

    public List<Serializable> getPeople(String args) throws IOException {
        String link = String.format("SELECT NAME FROM COMPANY WHERE WEBSITE= '%s'", args);
        String API_KEY = "5070adee780222eb8736e23778818139874c72b327680a01c0011e78fd922627";
        String query = URLEncoder.encode(link, StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        Integer employee = (jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count"));
        String name = (jsonObject.getJSONArray("data").getJSONObject(0).getString("name"));
        return List.of(employee, name);
    }
}
