package com.example.demo.parser;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

public class BrandfetchRequest {

    @SneakyThrows
    public Object getGeneral(String args) {
        HttpResponse<JsonNode> response = Unirest.get("https://api.brandfetch.io/v2/brands/" + args).header("Authorization", "Bearer S7vh48KwA2LcFJmo7bUdoXCGvi6oVqBnFUrodWTPNrg=").asJson();
        if (response.getStatus() != 200){
            return null;
        }
        JSONObject Jresult = new JSONObject();
        JSONObject jo = response.getBody().getObject();
        Object name = jo.get("name");
        Jresult.put("name", name);

        JSONArray links = (JSONArray) jo.get("links");
        Jresult.put("links", links);


        JSONArray logos = (JSONArray) jo.get("logos");
        for (Object font : logos) {
            JSONArray formats = ((JSONObject) font).getJSONArray("formats");
            int current_links = 0;
            Object src = "doesn`t exist";
            for (Object logo : formats) {
                if (current_links == 1) {
                    break;
                }
                try {
                    Object type = ((JSONObject) font).get("type");
                    src = ((JSONObject) logo).get("src");
                    Jresult.put((String) type, src);
                    current_links += 1;
                } catch (Exception e) {
                    continue;
                }
            }
            if (current_links == 0){
                Jresult.put((String) ((JSONObject) font).get("type"), src);
            }
        }
        return Jresult;
    }
}
