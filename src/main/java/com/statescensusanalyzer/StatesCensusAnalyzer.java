package com.statescensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;


public class StatesCensusAnalyzer {

    public int openCSVBuilder(Object className,String csvFile) throws CSVFileException {

        int counter=0;

        Object object=className;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFile));

        ) {

            CsvToBean<Object> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(object.getClass())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();


            Iterator<Object> iterator = csvToBean.iterator();

            while (iterator.hasNext()) {
                counter++;

                Object csvStates = iterator.next();

            }

        }
        catch (NoSuchFileException e) {

            if (csvFile.contains(".csv"))

                throw new CSVFileException("Please enter proper file name", CSVFileException.ExceptionType.NO_SUCH_FILE);

            else if (!csvFile.contains(".csv"))

                throw new CSVFileException("Please enter proper file type", CSVFileException.ExceptionType.NO_SUCH_FILE);

        }
        catch (RuntimeException e) {

                throw new CSVFileException("Exception Occurs due to incorrect delimiter position or Incorrect Header", CSVFileException.ExceptionType.NO_SUCH_FIELD);

            }
        catch (IOException e) {
            e.printStackTrace();
        }

        return counter;

    }


}