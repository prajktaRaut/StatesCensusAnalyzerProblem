package com.statescensusanalysertest;

import com.statescensusanalyzer.CSVFileException;
import com.statescensusanalyzer.CSVStates;
import com.statescensusanalyzer.CSVStatesCensus;
import com.statescensusanalyzer.StatesCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StatesCensusAnanlyserTest
{
    StatesCensusAnalyzer statesCensusAnalyzer=new StatesCensusAnalyzer();

    CSVStates csvStates=new CSVStates();
    CSVStatesCensus csvStatesCensus=new CSVStatesCensus();

    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCode.csv";

    private static final String SAMPLE_CSV_CENSUS_FILE_PATH = "/home/admin1/Documents/StatesCensusAnalyzer/src/test/resources/StateCensusData.csv";

   @Test
    public void givenMethod_CheckNumberOfRecordesMatchesOrNot_ShouldReturnTrue() throws CSVFileException, IOException
    {

            int value=statesCensusAnalyzer.openCSVBuilder(csvStates,SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(37,value);
    }

    @Test
    public void givenMethod_ifFoundIncorrectFileName_ShouldThrowException() throws IOException
    {
        try
        {
            int value = statesCensusAnalyzer.openCSVBuilder(csvStates,SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(37, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectFileType_ShouldThrowException() throws IOException
    {
        try
        {
            int value = statesCensusAnalyzer.openCSVBuilder(csvStates,SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(37, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);

        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPosition_ShouldReturnException() throws IOException,CSVFileException
    {
            try {

                int value = statesCensusAnalyzer.openCSVBuilder(csvStates,SAMPLE_CSV_FILE_PATH);
                Assert.assertEquals(37, value);
            }
             catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }

    @Test
    public void givenMethod_ifFoundIncorrectHeader_ShouldReturnException()
    {
        try {

            int value = statesCensusAnalyzer.openCSVBuilder(csvStates,SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(37, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }

    @Test
    public void givenMethod_CheckNumberOfRecordesMatchesOrNot_ForStatesCensus_ShouldReturnTrue() throws IOException, CSVFileException {

           int count = statesCensusAnalyzer.openCSVBuilder(csvStatesCensus,SAMPLE_CSV_CENSUS_FILE_PATH);
           Assert.assertEquals(29, count);

    }

    @Test
    public void givenMethod_ifFoundIncorrectName_OfStatesCensusFile_ShouldThrowException() throws IOException
    {
        try
        {
            int value = statesCensusAnalyzer.openCSVBuilder(csvStatesCensus,SAMPLE_CSV_CENSUS_FILE_PATH);
            Assert.assertEquals(29, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectType_OfStatesCensusFile_ShouldThrowException() throws IOException
    {
        try
        {
            int value = statesCensusAnalyzer.openCSVBuilder(csvStatesCensus,SAMPLE_CSV_CENSUS_FILE_PATH);
            Assert.assertEquals(29, value);

        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FILE,e.type);

        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPosition_InStatesCensusFile_ShouldReturnException() throws IOException,CSVFileException
    {
        try {

            int value = statesCensusAnalyzer.openCSVBuilder(csvStatesCensus,SAMPLE_CSV_CENSUS_FILE_PATH);
            Assert.assertEquals(29, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }

    @Test
    public void givenMethod_ifFoundIncorrectHeader_InStatesCensusFile_ShouldReturnException() throws IOException {
        try {

            int value = statesCensusAnalyzer.openCSVBuilder(csvStatesCensus,SAMPLE_CSV_CENSUS_FILE_PATH);
            Assert.assertEquals(29, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }


}
