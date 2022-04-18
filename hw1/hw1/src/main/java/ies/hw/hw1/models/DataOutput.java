package ies.hw.hw1.models;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import net.minidev.json.JSONArray;

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
            JSONObject json2 = (JSONObject) ((JSONArray) json.get("request")).get(0);
            JSONObject cases = (JSONObject) json.get("cases");
            country = json2.get("country").toString();
            String newAct = json2.get("new").toString();
            newAct.split("+");
            // TODO
        }
        else {
            date = LocalDate.parse(json.get("date").toString());
            active = (Long) json.get("active");
            newActive = (Long) json.get("active_diff");
            deaths = (Long) json.get("deaths");
            newDeaths = (Long) json.get("deaths_diff");
            recovered = (Long) json.get("recovered");
            country = ((JSONObject) json.get("region")).get("name").toString();
        }

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
