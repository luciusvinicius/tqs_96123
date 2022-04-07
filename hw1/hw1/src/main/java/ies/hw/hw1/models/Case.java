package ies.hw.hw1.models;

public class Case {
    private String newCases;
    private int active;
    private int critical;
    private int recovered;
    private String pop1M;
    private int total;

    public Case(String newCases, int active, int critical, int recovered, String pop1m, int total) {
        this.newCases = newCases;
        this.active = active;
        this.critical = critical;
        this.recovered = recovered;
        pop1M = pop1m;
        this.total = total;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String getPop1M() {
        return pop1M;
    }

    public void setPop1M(String pop1m) {
        pop1M = pop1m;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
}
