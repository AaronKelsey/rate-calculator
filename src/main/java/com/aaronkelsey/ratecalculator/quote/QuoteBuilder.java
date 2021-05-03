package com.aaronkelsey.ratecalculator.quote;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

public class QuoteBuilder {
    private final DecimalFormat oneDecimalPlace;
    private final DecimalFormat twoDecimalPlace;

    private StringBuilder quoteString;

    public QuoteBuilder() {
        quoteString = new StringBuilder();
        oneDecimalPlace = new DecimalFormat("#.0");
        twoDecimalPlace = new DecimalFormat("#.00");
        oneDecimalPlace.setRoundingMode(RoundingMode.HALF_UP);
        twoDecimalPlace.setRoundingMode(RoundingMode.HALF_UP);
    }

    public String PrintQuote(Optional<Quote> quote) {
        if (quote.isPresent()) {
            appendWithNewLine("Requested amount: £" + quote.get().getRequestedAmount());
            appendWithNewLine("Annual Interest Rate: " + oneDecimalPlace.format(quote.get().getAnnualInterestRate() * 100) + "%");
            appendWithNewLine("Monthly repayment: £" + twoDecimalPlace.format(quote.get().getMonthlyPayments()));
            appendWithNewLine("Total repayment: £" + twoDecimalPlace.format(quote.get().getTotalPayment()));
        }
        else {
            System.out.println("Failed to print quote");
        }

        return quoteString.toString();
    }

    private void appendWithNewLine(String value) {
        quoteString.append(value).append(System.lineSeparator());
    }
}
