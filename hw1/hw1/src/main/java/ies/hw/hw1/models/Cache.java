package ies.hw.hw1.models;

public class Cache {

    private int numberOfHits;
    private int numberOfMisses;
    private long ttl;
    
    public Cache(int numberOfHits, int numberOfMisses, long ttl) {
        this.numberOfHits = numberOfHits;
        this.numberOfMisses = numberOfMisses;
        this.ttl = ttl;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public void setNumberOfHits(int numberOfHits) {
        this.numberOfHits = numberOfHits;
    }

    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    public void setNumberOfMisses(int numberOfMisses) {
        this.numberOfMisses = numberOfMisses;
    }

    public int getNumberOfRequests() {
        return numberOfHits + numberOfMisses;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
