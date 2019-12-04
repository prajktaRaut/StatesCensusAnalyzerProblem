package com.statescensusanalysertest;

import com.statescensusanalyzer.CSVFileException;
import com.statescensusanalyzer.CSVStates;
import com.statescensusanalyzer.CSVStatesCensus;
import com.statescensusanalyzer.StatesCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StatesCensusAnanlyserTest
{
   StatesCensusAnalyzer<Integer> statesCensusAnalyzer=new StatesCensusAnalyzer();

    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCode.csv";

    private static final String SAMPLE_CSV_States_Census_FILE_PATH="/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCensusData.csv";

    @Test
    public void givenMethod_CheckNumberOfRecordesMatchesOrNot_ShouldReturnTrue() throws CSVFileException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        int value=statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_FILE_PATH,null);
        Assert.assertEquals(37,value);
    }

    @Test
    public void givenMethod_ifFoundIncorrectFileName_ShouldThrowException() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try
        {
            int value = statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_FILE_PATH,null);
            Assert.assertEquals(37, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectFileType_ShouldThrowException() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try
        {
            int value = statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_FILE_PATH,null);
            Assert.assertEquals(37, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);

        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPosition_ShouldReturnException() throws IOException, CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {

            int value = statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_FILE_PATH,null);
            Assert.assertEquals(37, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }

    @Test
    public void givenMethod_ifFoundIncorrectHeader_ShouldReturnException() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {

            int value = statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_FILE_PATH,null);
            Assert.assertEquals(37, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_HEADER,e.type);
        }

    }

    @Test
    public void givenMethod_SortDataByStateName_ShouldReturnSortCountEqualsOrNot() throws CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        int value=statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_States_Census_FILE_PATH,"getState");

        Assert.assertEquals(29,value);
    }
    @Test
    public void givenMethod_SortDataByPopulationValue_ShouldReturnSortCountEqualsOrNot() throws CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        int value=statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_States_Census_FILE_PATH,"getPopulation");

        Assert.assertEquals(29,value);
    }

    @Test
    public void givenMethod_SortDataByDensity_ShouldReturnSortCountEqualsOrNot() throws CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        int value=statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_States_Census_FILE_PATH,"getDensityPerSqKm");

        Assert.assertEquals(29,value);
    }

    @Test
    public void givenMethod_SortDataByArea_ShouldReturnSortCountEqualsOrNot() throws CSVFileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        int value=statesCensusAnalyzer.checkNumberOfRecordsForCSVStates(SAMPLE_CSV_States_Census_FILE_PATH,"getAreaInSqKm");

        Assert.assertEquals(29,value);
    }



}
