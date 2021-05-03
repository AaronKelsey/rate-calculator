package com.aaronkelsey.ratecalculator.quote;

public class Quote {

    private final int requestedAmount;
    private final double annualInterestRate;
    private final double monthlyPayments;
    private final double totalPayment;

    public Quote(int requestedAmount, double annualInterestRate, double monthlyPayments, double totalPayment){
        this.requestedAmount = requestedAmount;
        this.annualInterestRate = annualInterestRate;
        this.monthlyPayments = monthlyPayments;
        this.totalPayment = totalPayment;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getMonthlyPayments() {
        return monthlyPayments;
    }

    public double getTotalPayment() {
        return totalPayment;
    }
}
