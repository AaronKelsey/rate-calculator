package com.aaronkelsey.ratecalculator.quote;

import com.aaronkelsey.ratecalculator.lender.Lender;
import com.aaronkelsey.ratecalculator.utils.CSVParser;
import com.aaronkelsey.ratecalculator.utils.FailedToReadLenderInformationException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class QuoteService {

    private static final double PAYMENTS_PER_YEAR = 12.0;
    private static final double LOAN_DURATION_IN_YEARS = 3.0;

    private static final int MINIMUM_AMOUNT = 1000;
    private static final int MAXIMUM_AMOUNT = 15000;
    private static final int INCREMENTS_OF = 100;

    private List<Lender> lenders;

    public Optional<Quote> requestQuote(int requestedAmount) {
        if (!validateRequestedAmount(requestedAmount)) {
            return Optional.empty();
        }

        if (!confirmRequestedAmountIsAvailable(requestedAmount)) {
            return Optional.empty();
        }

        Optional<Quote> quote = calculateQuote(requestedAmount);

        if (quote.isPresent()) {
            QuoteBuilder quoteBuilder = new QuoteBuilder();
            System.out.println(quoteBuilder.PrintQuote(quote));
        }

        return quote;
    }

    private boolean validateRequestedAmount(int requestedAmount) {
        boolean isAmountValid = true;

        if (requestedAmount < MINIMUM_AMOUNT || requestedAmount > MAXIMUM_AMOUNT) {
            System.out.println("Please enter a number between £" + MINIMUM_AMOUNT + " and £" + MAXIMUM_AMOUNT);
            isAmountValid = false;
        }

        else if (requestedAmount % INCREMENTS_OF != 0) {
            System.out.println("The requested amount must be in increments of £" + INCREMENTS_OF);
            isAmountValid = false;
        }

        return isAmountValid;
    }

    private List<Lender> initialiseListOfLenders() {
        List<Lender> lenderListCSV  = null;

        try {
            lenderListCSV = CSVParser.parse("LenderInformation.csv");
            lenderListCSV.sort(Comparator.comparingDouble(Lender::getRate));
        }
        catch (FailedToReadLenderInformationException exception) {
            System.out.println(exception.getMessage());
        }

        return lenderListCSV;
    }

    private boolean confirmRequestedAmountIsAvailable(int requestedAmount) {
        boolean sufficientFunds = true;

        int availableAmount = 0;

        lenders = initialiseListOfLenders();
        if (lenders == null) {
            return false;
        }

        for (Lender lender : lenders){
            availableAmount += lender.getAmountAvailable();
        }

        if (requestedAmount > availableAmount) {
            System.out.println("It is not possible to provide a quote.");
            sufficientFunds = false;
        }

        return sufficientFunds;
    }

    private Optional<Quote> calculateQuote(int requestedAmount) {
        double totalInterest = 0.0;
        int remainingAmount = requestedAmount;

        for (Lender lender : lenders) {
            if (remainingAmount == 0) {
                break;
            }

            // Calculate blended interest rate
            int borrowedAmount = Math.min(remainingAmount, lender.getAmountAvailable());
            totalInterest += borrowedAmount * lender.getRate();
            remainingAmount -= borrowedAmount;
        }

        double annualRate = totalInterest / requestedAmount;

        double effectiveMonthlyInterestRate = calculateEffectiveMonthlyInterestRate(annualRate);
        double monthlyPayment = calculateMonthlyPayments(effectiveMonthlyInterestRate, requestedAmount);
        double totalPayment = calculateTotalAmount(monthlyPayment);

        return Optional.of(new Quote(requestedAmount, annualRate, monthlyPayment, totalPayment));
    }

    private double calculateEffectiveMonthlyInterestRate(double annualRate){
        return Math.pow((1 + annualRate), (1 / PAYMENTS_PER_YEAR)) - 1;
    }

    private double calculateMonthlyPayments(double monthlyInterestRate, int requestedAmount){
        return monthlyInterestRate * requestedAmount /
                (1 - Math.pow((1 + monthlyInterestRate), -loanDuration()));
    }

    private double calculateTotalAmount(double monthlyPayment){
        return monthlyPayment * loanDuration();
    }

    private double loanDuration() {
        return PAYMENTS_PER_YEAR * LOAN_DURATION_IN_YEARS;
    }
}
