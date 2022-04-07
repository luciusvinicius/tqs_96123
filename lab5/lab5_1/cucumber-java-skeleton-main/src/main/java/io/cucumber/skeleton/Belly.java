package io.cucumber.skeleton;

public class Belly {
    public void eat(int cukes) {
        System.out.println("Belly just ate " + cukes + " cukes!");
    }

    public void wait(int time) {
        System.out.println("Belly just waited for " + time + " hours!");
    }

    public void grow() {
        System.out.println("Belly just grew!");
    }
}
