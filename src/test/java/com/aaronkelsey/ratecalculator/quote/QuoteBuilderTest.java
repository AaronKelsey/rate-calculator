package com.aaronkelsey.ratecalculator.quote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class QuoteBuilderTest {

    @Test
    void printQuote_PrintsQuoteInCorrectFormat_Success(){
        QuoteBuilder quoteBuilder = new QuoteBuilder();

        StringBuilder correctQuoteString = new StringBuilder();
        correctQuoteString.append("Requested amount: £1000").append(System.lineSeparator());
        correctQuoteString.append("Annual Interest Rate: 7.0%").append(System.lineSeparator());
        correctQuoteString.append("Monthly repayment: £30.78").append(System.lineSeparator());
        correctQuoteString.append("Total repayment: £1108.10").append(System.lineSeparator());

        Optional<Quote> quote = Optional.of(new Quote(1000, 0.07, 30.78, 1108.10));

        Assertions.assertEquals(correctQuoteString.toString(), quoteBuilder.PrintQuote(quote));
    }

}
