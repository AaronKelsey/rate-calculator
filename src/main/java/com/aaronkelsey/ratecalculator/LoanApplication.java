package com.aaronkelsey.ratecalculator;

import com.aaronkelsey.ratecalculator.quote.Quote;
import com.aaronkelsey.ratecalculator.quote.QuoteService;

import java.util.Optional;

public class LoanApplication {

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Incorrect number of arguments. This program accepts one argument [loan_amount]");
            return;
        }

        int requestedAmount = validateArgument(args);

        if (requestedAmount > 0) {
            QuoteService quoteService = new QuoteService();
            Optional<Quote> quote = quoteService.requestQuote(requestedAmount);
        }
        else {
            System.out.println("Please enter a valid numerical amount");
        }
    }

    private static int validateArgument(String[] args) {
        int requestedAmount;

        try {
            requestedAmount = Integer.parseInt(args[0]);
        }
        catch (final NumberFormatException exception) {
            requestedAmount = -1;
        }

        return requestedAmount;
    }
}
