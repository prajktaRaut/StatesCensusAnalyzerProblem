package com.statescensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StatesCensusAnalyzer {

    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCode.csv";

    public int checkNumberOfRecords() throws IOException, CSVFileException {

            int count = 0;

            try(
                    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            ){
                CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CSVStates.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<CSVStates> iterator = csvToBean.iterator();

                while (iterator.hasNext()) {
                    count++;

                    CSVStates csvStates = iterator.next();

                    System.out.println("SerialNumber :" + csvStates.getSrNo());
                    System.out.println("StateName :" + csvStates.getStateName());
                    System.out.println("TIN :" + csvStates.getTIN());
                    System.out.println("StateCode :" + csvStates.getStateCode());
                    System.out.println("====================================");


                }
            }
            catch (RuntimeException e)
            {
                throw new CSVFileException("Exception due to incorrect delimiter position",CSVFileException.ExceptionType.NO_SUCH_FIELD);

            }
            catch (NoSuchFileException e)
            {
                 throw new CSVFileException("Please enter proper file type",CSVFileException.ExceptionType.NO_SUCH_FILE);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        return count;
    }

}