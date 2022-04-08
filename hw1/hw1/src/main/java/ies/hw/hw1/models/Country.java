package ies.hw.hw1.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Country {

    private String continent;
    private String country; 
    private long population;
    private String day;
    private String time;

    // private int newDeaths;
    // private int totalDeaths;

    // private int newCases;
    // private int activeCases;
    // private int criticalCases;
    // private int recoveredCases;
    // private int totalCases;

    public Country() {
        
    }

    @Override
    public String toString() {
        return "Country [continent=" + continent + ", country=" + country + ", day=" + day + ", population="
                + population + ", time=" + time + "]";
    }

    public Country(String continent, String country, long population, String day, String time) {
        this.continent = continent;
        this.country = country;
        this.population = population;
        this.day = day;
        this.time = time;
    }


    public String getContinent() {
        return continent;
    }


    public void setContinent(String continent) {
        this.continent = continent;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public long getPopulation() {
        return population;
    }


    public void setPopulation(long population) {
        this.population = population;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    // public int getNewDeaths() {
    //     return newDeaths;
    // }


    // public void setNewDeaths(int newDeaths) {
    //     this.newDeaths = newDeaths;
    // }


    // public int getTotalDeaths() {
    //     return totalDeaths;
    // }


    // public void setTotalDeaths(int totalDeaths) {
    //     this.totalDeaths = totalDeaths;
    // }


    // public int getNewCases() {
    //     return newCases;
    // }


    // public void setNewCases(int newCases) {
    //     this.newCases = newCases;
    // }


    // public int getActiveCases() {
    //     return activeCases;
    // }


    // public void setActiveCases(int activeCases) {
    //     this.activeCases = activeCases;
    // }


    // public int getCriticalCases() {
    //     return criticalCases;
    // }


    // public void setCriticalCases(int criticalCases) {
    //     this.criticalCases = criticalCases;
    // }


    // public int getRecoveredCases() {
    //     return recoveredCases;
    // }


    // public void setRecoveredCases(int recoveredCases) {
    //     this.recoveredCases = recoveredCases;
    // }


    // public int getTotalCases() {
    //     return totalCases;
    // }


    // public void setTotalCases(int totalCases) {
    //     this.totalCases = totalCases;
    // }
}
