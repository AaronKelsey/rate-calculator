package com.aaronkelsey.ratecalculator.utils;

import com.aaronkelsey.ratecalculator.lender.Lender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVParserTest {
    private final String errorMessage = "Failed to read market information.";

    @Test
    public void CSVParser_loadsAllLenders_Success() throws FailedToReadLenderInformationException {
        String csvFilename = "LenderInformation.csv";
        List<Lender> lenderListCSV = CSVParser.parse(csvFilename);
        Assertions.assertEquals(7, lenderListCSV.size());
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
