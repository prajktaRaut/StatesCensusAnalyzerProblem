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


    public int checkNumberOfRecordsForCSVStates(String SAMPLE_CSV_FILE_PATH ) throws CSVFileException {

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


    public int checkNumberOfRecordsForCSVStatesCensus(String SAMPLE_CSV_CENSUS_FILE_PATH ) throws IOException, CSVFileException {

        int count = 0;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_CENSUS_FILE_PATH));

        ) {

            CsvToBean<CSVStatesCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStatesCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStatesCensus> iterator=csvToBean.iterator();
            while (iterator.hasNext()) {
                count++;

                CSVStatesCensus csvStatesCensus=iterator.next();

                if (csvStatesCensus.getState()==null || csvStatesCensus.getPopulation()==null || csvStatesCensus.getAreaInSqKm()==null || csvStatesCensus.getDensityPerSqKm()==null){

                    throw new CSVFileException("Exception due to incorrect Header", CSVFileException.ExceptionType.NO_SUCH_HEADER);

                }


                }
            }
        catch (NoSuchFileException e){

            if (SAMPLE_CSV_CENSUS_FILE_PATH.contains(".csv"))
                throw new CSVFileException("Please enter proper file name",CSVFileException.ExceptionType.NO_SUCH_FILE);
            else if (!SAMPLE_CSV_CENSUS_FILE_PATH.contains(".csv"))
                throw new CSVFileException("Please enter proper file type",CSVFileException.ExceptionType.NO_SUCH_FILE);

        }
        catch (RuntimeException e){

            throw new CSVFileException("Exception occure due to wrong position of delimiter",CSVFileException.ExceptionType.INVALID_DELIMITER);

        }

        catch (IOException e) {
            e.printStackTrace();
        }


        return count;
    }



}