package com.aaronkelsey.ratecalculator.utils;

import com.aaronkelsey.ratecalculator.lender.Lender;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVParser {

    public static List<Lender> parse(String filename) throws NullPointerException, FailedToReadLenderInformationException {
        List<Lender> lenderList = new ArrayList<>();

        BufferedReader bufferedReader = null;
        CSVReader csvReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(CSVParser.class.getResourceAsStream("/" + filename))));
            csvReader = new CSVReader(bufferedReader);

            String headerLine = bufferedReader.readLine();
            if (!headerLine.equals("Lender,Rate,Available")) {
                throw new CsvRequiredFieldEmptyException("Invalid CSV format.");
            }

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                lenderList.add(new Lender(nextRecord[0], Double.parseDouble(nextRecord[1]), Integer.parseInt(nextRecord[2])));
            }
        }
        catch (CsvRequiredFieldEmptyException | CsvValidationException | IOException | NullPointerException | NumberFormatException exception) {
            throw new FailedToReadLenderInformationException("Failed to read market information.");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (csvReader != null) {
                    csvReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lenderList;
    }
}
