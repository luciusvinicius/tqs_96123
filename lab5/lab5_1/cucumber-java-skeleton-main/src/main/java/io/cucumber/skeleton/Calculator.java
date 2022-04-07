package io.cucumber.skeleton;

public class Calculator {

    private int[] vals = new int[2];
    private boolean isNew = true;
    private int value = 0;
    
    public void push(int val) {
        if (isNew) {
            vals[0] = val;
            isNew = false;
        }
        else {
            vals[1] = val;
        }
    }

    public void push(String op) {
        switch (op) {
            case "+":
                value = vals[0] + vals[1];
                break;
            case "-":
                value = vals[0] - vals[1];
                break;
            default:
                System.out.println("Sorry, I couldn't understand it");
        }
        
    }

    public int value() {
        isNew = true;
        vals = new int[2];
        int ret = value;
        value = 0;
        return ret;
    }

}
