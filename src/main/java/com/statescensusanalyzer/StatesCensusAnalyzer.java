package com.statescensusanalyzer;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;


public class StatesCensusAnalyzer <T extends Comparable> {


    String SAMPLE_GSON_File_PATH="/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/SortedDataByState.json";


    public int checkNumberOfRecordsForCSVStates(String SAMPLE_CSV_FILE_PATH, String methodName) throws CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

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


            genericSortMethod(list,methodName);
            writeToJson(list,SAMPLE_GSON_File_PATH);




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


    }

    public void sortPopulationsValueInDescendingOrder(List<CSVStatesCensus> list)
    {
        int count=0;
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getPopulation()) - Integer.parseInt(s1.getPopulation());
        list.sort(c);

    }

    public void sortDensityValueInDescendingOrder(List<CSVStatesCensus> list)
    {
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getDensityPerSqKm()) - Integer.parseInt(s1.getDensityPerSqKm());
        list.sort(c);

    }

    public void sortAreaValueInDescendingOrder(List<CSVStatesCensus> list)
    {
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getAreaInSqKm()) - Integer.parseInt(s1.getAreaInSqKm());
        list.sort(c);

    }


    public void writeToJson(List<CSVStatesCensus> statesCensusList,String SAMPLE_FILE_PATH) throws IOException {
        Gson gson=new Gson();
        String json=gson.toJson(statesCensusList);
        FileWriter fileWriter=new FileWriter(SAMPLE_FILE_PATH);
        fileWriter.write(json);
        fileWriter.close();

    }


    public void sort(List<CSVStatesCensus> list) throws IOException {

        for (int i=0;i<list.size()-1;i++)
        {
            for (int j=0;j<list.size()-i-1;j++)
            {
                if (list.get(j).getState().compareTo(list.get(j+1).getState())>0)
                {

                    CSVStatesCensus temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);

                }

            }

        }

        writeToJson(list,SAMPLE_GSON_File_PATH);

    }

    public void genericSortMethod(List<CSVStatesCensus> list, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i=0;i<list.size()-1;i++)
        {
            for (int j=0;j<list.size()-i-1;j++)
            {

                Class newClass1=list.get(j).getClass();
                Method callMethod=newClass1.getDeclaredMethod(methodName);
                T value1= (T) callMethod.invoke(list.get(j));

                Class newClass2=list.get(j+1).getClass();
                Method method=newClass2.getDeclaredMethod(methodName);
                T value2= (T) method.invoke(list.get(j+1));

                if (value1.compareTo(value2)<0)
                {
                    CSVStatesCensus temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);

                }
            }
        }
    }

}






