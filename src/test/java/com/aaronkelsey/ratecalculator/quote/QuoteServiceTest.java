package com.aaronkelsey.ratecalculator.quote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class QuoteServiceTest {

    private static Optional<Quote> successfulQuote;

    @BeforeAll
    static void setUp() {
        QuoteService quoteService = new QuoteService();
        successfulQuote = quoteService.requestQuote(1000);
    }

    @Test
    void requestQuote_CalculateAnnualInterestRate_Success(){
        Assertions.assertTrue(successfulQuote.isPresent());
        Assertions.assertEquals(0.07, Math.round(successfulQuote.get().getAnnualInterestRate() * 100.0) / 100.0);
    }

    @Test
    void requestQuote_CalculateMonthlyPayments_Success(){
        Assertions.assertTrue(successfulQuote.isPresent());
        Assertions.assertEquals(30.78, Math.round(successfulQuote.get().getMonthlyPayments() * 100.0) / 100.0);
    }

    @Test
    void requestQuote_testCalculateTotalAmount_Success(){
        Assertions.assertTrue(successfulQuote.isPresent());
        Assertions.assertEquals(1108.10, Math.round(successfulQuote.get().getTotalPayment() * 100.0) / 100.0);
    }

    @Test
    void requestQuote_MarketDoesNotHaveEnoughFunds_Failure(){
        QuoteService quoteService = new QuoteService();
        Optional<Quote> invalidQuote = quoteService.requestQuote(2400);

        Assertions.assertFalse(invalidQuote.isPresent());
    }

    @Test
    void requestQuote_RequestedAmountLowerThanMinimum_Failure(){
        QuoteService quoteService = new QuoteService();
        Optional<Quote> invalidQuote = quoteService.requestQuote(900);

        Assertions.assertFalse(invalidQuote.isPresent());
    }

    @Test
    void requestQuote_RequestedAmountLargerThanMaximum_Failure(){
        QuoteService quoteService = new QuoteService();
        Optional<Quote> invalidQuote = quoteService.requestQuote(15100);

        Assertions.assertFalse(invalidQuote.isPresent());
    }

    @Test
    void requestQuote_RequestedAmountNotIncrementOfOneHundred_Failure(){
        QuoteService quoteService = new QuoteService();
        Optional<Quote> invalidQuote = quoteService.requestQuote(1050);

        Assertions.assertFalse(invalidQuote.isPresent());
    }
}