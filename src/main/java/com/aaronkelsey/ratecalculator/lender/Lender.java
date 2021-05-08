package com.aaronkelsey.ratecalculator.lender;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lender lender = (Lender) o;
        return Double.compare(lender.rate, rate) == 0 && amountAvailable == lender.amountAvailable && Objects.equals(name, lender.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, amountAvailable);
    }
}
