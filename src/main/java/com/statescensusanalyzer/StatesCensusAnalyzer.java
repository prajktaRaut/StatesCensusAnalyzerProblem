package com.statescensusanalyzer;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;


public class StatesCensusAnalyzer {


    String SAMPLE_GSON_FILE_PATH="/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StatesCensus.json";

    public int checkNumberOfRecordsForCSVStates(String SAMPLE_CSV_FILE_PATH) throws CSVFileException {

        int count = 0;

        List<CSVStatesCensus> list=new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

        ) {

            CsvToBean<CSVStatesCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStatesCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStatesCensus> iterator=csvToBean.iterator();


            while (iterator.hasNext()) {
                count++;

                CSVStatesCensus csvStatesCensus = iterator.next();

                list.add(csvStatesCensus);

            }
            sortBYPopulationsValue(list);
            writeToJson(list);

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

    public void sortBYStatesName(List<CSVStatesCensus> list)
    {
        Comparator<CSVStatesCensus> comparator=Comparator.comparing(CSVStatesCensus::getState);
        list.sort(comparator);
        System.out.println(list.toString());

    }

    public void sortBYPopulationsValue(List<CSVStatesCensus> list)
    {
        Comparator<CSVStatesCensus> comparator=Comparator.comparing(CSVStatesCensus::getPopulation);
        list.sort(comparator);
        System.out.println(list.toString());

    }


    public void writeToJson(List<CSVStatesCensus> statesCensusList) throws IOException {
        Gson gson=new Gson();
        String json=gson.toJson(statesCensusList);
        FileWriter fileWriter=new FileWriter(SAMPLE_GSON_FILE_PATH);
        fileWriter.write(json);
        fileWriter.close();

    }





}






