package com.aaronkelsey.ratecalculator.lender;

public class Lender {

    private final String name;
    private final double rate;
    private final int amountAvailable;

    public Lender(String name, double rate, int amountAvailable){
        this.name = name;
        this.rate = rate;
        this.amountAvailable = amountAvailable;
    }

    public String getName() {
        return name;
    }

    public double getRate(){
        return rate;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }
}
