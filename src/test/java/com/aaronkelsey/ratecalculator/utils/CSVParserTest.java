package com.aaronkelsey.ratecalculator.utils;

import com.aaronkelsey.ratecalculator.lender.Lender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVParserTest {
    private final String errorMessage = "Failed to read market information.";

    private static final List<Lender> EXPECTED_LENDERS = List.of(
            new Lender("Bob", 0.075, 640),
            new Lender("Jane", 0.069, 480),
            new Lender("Fred", 0.071, 520),
            new Lender("Mary", 0.104, 170),
            new Lender("John", 0.081, 320),
            new Lender("Dave", 0.074, 140),
            new Lender("Angela", 0.071, 60));

    @Test
    public void CSVParser_loadsAllLenders_Success() throws FailedToReadLenderInformationException {
        String csvFilename = "LenderInformation.csv";
        List<Lender> lenderListCSV = CSVParser.parse(csvFilename);
        Assertions.assertEquals(7, lenderListCSV.size());
    }

    @Test
    public void CSVParser_returnsLenderListWithCorrectContent_Success() throws FailedToReadLenderInformationException {
        String csvFilename = "LenderInformation.csv";
        List<Lender> lenderListCSV = CSVParser.parse(csvFilename);
        Assertions.assertEquals(EXPECTED_LENDERS, lenderListCSV);
    }

    @Test
    public void CSVParser_CsvFileWithMissingHeader_ThrowsException() {
        FailedToReadLenderInformationException exception = Assertions.assertThrows(
                FailedToReadLenderInformationException.class,
                () -> CSVParser.parse("LenderInformationMissingHeader.csv"));

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void CSVParser_CsvFileWithIncorrectContent_ThrowsException() {
        FailedToReadLenderInformationException exception = Assertions.assertThrows(
                FailedToReadLenderInformationException.class,
                () -> CSVParser.parse("LenderInformationIncorrectContent.csv"));

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void CSVParser_NonExistentFileProvided_ThrowsException() {
        Assertions.assertThrows(
                FailedToReadLenderInformationException.class,
                () -> CSVParser.parse("IncorrectFilename.csv"));
    }
}
