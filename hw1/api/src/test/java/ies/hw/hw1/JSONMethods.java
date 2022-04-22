package ies.hw.hw1;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONMethods {

    public JSONArray generateJSONArray(String s) throws ParseException {
        return (JSONArray) new JSONParser().parse(s);
    }

    public JSONObject generateJSONObject(String s) throws ParseException {
        return (JSONObject) new JSONParser().parse(s);
    }

    public List<JSONObject> jsonArrayToListOfObjects(JSONArray array) {
        List<JSONObject> retJson = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            retJson.add((JSONObject) array.get(i));
        }

        return retJson;
    }
}
