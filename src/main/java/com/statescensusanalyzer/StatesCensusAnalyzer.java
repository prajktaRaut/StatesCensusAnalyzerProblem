package com.statescensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StatesCensusAnalyzer {

    private static final String SAMPLE_CSV_FILE_PATH="/home/admin1/Desktop/StateCode.csv";

    public int checkNumberOfRecords() throws IOException {
        int count = 0;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {

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

        return count;
    }

}
