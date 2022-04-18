package ies.hw.hw1.models;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class DataOutput {

    private String country;
    private LocalDate date;
    private Long active;
    private Long newActive;
    private Long deaths;
    private Long newDeaths;
    private Long recovered;

    public DataOutput(JSONObject json, boolean api1) {

        if (api1) {
            JSONObject json2 = (JSONObject) ((JSONArray) json.get("response")).get(0);
            System.out.println("JSON 2");
            System.out.println(json2);
            JSONObject cases = (JSONObject) json2.get("cases");
            country = json2.get("country").toString();
            String newAct = cases.get("new").toString();
            newActive = Long.parseLong(newAct.substring(1));
            active = (Long) cases.get("active");
            recovered = (Long) cases.get("recovered");

            JSONObject deathsJson = (JSONObject) json2.get("deaths");
            deaths = (Long) deathsJson.get("total");
            String newDeathsJson = deathsJson.get("new").toString();
            newDeaths = Long.parseLong(newDeathsJson.substring(1));
            date = LocalDate.parse(json2.get("day").toString());
        }
        else {
            System.out.println(json);
            date = LocalDate.parse(json.get("date").toString());
            active = (Long) json.get("active");
            newActive = (Long) json.get("active_diff");
            deaths = (Long) json.get("deaths");
            newDeaths = (Long) json.get("deaths_diff");
            recovered = (Long) json.get("recovered");
            country = ((JSONObject) json.get("region")).get("name").toString();
        }

    }

    public DataOutput(JSONObject json) { // API2 utilizes this sum
        JSONArray arr = (JSONArray) json.get("data");

        if (arr.isEmpty()) {
            return;
        }

  
        DataOutput baseData = new DataOutput( (JSONObject) arr.get(0), false);

        for (int i = 1; i < arr.size(); i++) {
            DataOutput newData = new DataOutput((JSONObject) arr.get(i), false);
            baseData.setDeaths(baseData.getDeaths() + newData.getDeaths());
            baseData.setRecovered(baseData.getRecovered() + newData.getRecovered());
            baseData.setActive(baseData.getActive() + newData.getActive());
            baseData.setNewActive(baseData.getNewActive() + newData.getNewActive());
            baseData.setNewDeaths(baseData.getNewDeaths() + newData.getNewDeaths());
        }
        

        active = baseData.getActive();
        country = baseData.getCountry();
        date = baseData.getDate();
        deaths = baseData.getDeaths();
        newActive = baseData.getNewActive();
        newDeaths = baseData.getNewDeaths();
        recovered = baseData.getRecovered();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getNewActive() {
        return newActive;
    }

    public void setNewActive(Long newActive) {
        this.newActive = newActive;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }
    
}
