package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJO = new JSONObject(json);
            JSONObject nameJO = sandwichJO.getJSONObject("name");
            String mainName = nameJO.getString("mainName");
            JSONArray jsonArray = nameJO.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = getJsonArray(jsonArray);
            String placeOfOrigin = sandwichJO.getString("placeOfOrigin");
            String description = sandwichJO.getString("description");
            String image = sandwichJO.getString("image");
            jsonArray = sandwichJO.getJSONArray("ingredients");
            List<String> ingredients = getJsonArray(jsonArray);
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> getJsonArray(JSONArray jsonArray) throws JSONException {
        List<String> alsoKnownAs = new ArrayList<>(jsonArray.length());
        int len = jsonArray.length();
        for (int i = 0; i < len; i++) {
            alsoKnownAs.add(jsonArray.get(i).toString());
        }
        return alsoKnownAs;
    }
}
