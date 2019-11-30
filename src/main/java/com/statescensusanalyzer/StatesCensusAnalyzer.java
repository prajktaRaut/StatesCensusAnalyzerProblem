package com.statescensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StatesCensusAnalyzer {

    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCode.csv";

    public int checkNumberOfRecords() throws CSVFileException {

        int count = 0;
        String[] columns={};

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

        ) {

            CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStates.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (int i=0;i<columns.length;i++) {
                System.out.println("Columns are "+columns[i]);
            }

            Iterator<CSVStates> iterator = csvToBean.iterator();

            while (iterator.hasNext()) {
                count++;

                CSVStates csvStates = iterator.next();

                if (csvStates.getSrNo()==0 || csvStates.getStateName()==null || csvStates.getTIN()==null || csvStates.getStateCode()==null) {
                    throw new CSVFileException("Exception due to Header", CSVFileException.ExceptionType.NO_SUCH_HEADER);
                }
            }

        }
        catch (NoSuchFileException e) {

            if (SAMPLE_CSV_FILE_PATH.contains(".csv"))

                throw new CSVFileException("Please enter proper file name", CSVFileException.ExceptionType.NO_SUCH_FILE);

            else if (!SAMPLE_CSV_FILE_PATH.contains(".csv"))

                throw new CSVFileException("Please enter proper file type", CSVFileException.ExceptionType.NO_SUCH_FILE);

        }
        catch (RuntimeException e) {

                throw new CSVFileException("Exception due to incorrect delimiter position", CSVFileException.ExceptionType.NO_SUCH_FIELD);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

}