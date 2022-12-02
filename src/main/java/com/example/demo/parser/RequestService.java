package com.example.demo.parser;


import lombok.SneakyThrows;
import org.json.JSONObject;

import java.util.List;

public class RequestService {


    public final BrandfetchRequest brandfetchRequest;
    public final PDLReader pdlReader;
    public final GooglePlaces googlePlaces;

    public RequestService() {

        brandfetchRequest = new BrandfetchRequest();
        pdlReader = new PDLReader();
        googlePlaces = new GooglePlaces();

    }


    @SneakyThrows
    public Object getData(String args){
        String link = args;
        Object json = brandfetchRequest.getGeneral(link);
        if (json == null){
            return null;
        }
        List employees = pdlReader.getPeople(link);
        String address = googlePlaces.getLocation(link);
        ((JSONObject) json).put("employees", employees.get(0));
         if (((JSONObject) json).get("name").getClass().getSimpleName().equals("Null")){;
            ((JSONObject) json).remove("name");
            ((JSONObject) json).put("name", employees.get(1));
       }
        ((JSONObject) json).put("address", address);
        return json;
    }
}
