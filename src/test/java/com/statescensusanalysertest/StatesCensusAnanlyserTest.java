package com.statescensusanalysertest;

import com.statescensusanalyzer.CSVFileException;
import com.statescensusanalyzer.StatesCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StatesCensusAnanlyserTest
{
    StatesCensusAnalyzer statesCensusAnalyzer=new StatesCensusAnalyzer();
   @Test
    public void givenMethod_CheckNumberOfRecordesMatchesOrNot_ShouldReturnTrue() throws CSVFileException, IOException
    {
        int value=statesCensusAnalyzer.checkNumberOfRecords();
        Assert.assertEquals(37,value);

    }

    @Test
    public void givenMethod_ifFoundIncorrectFileName_ShouldThrowException() throws IOException
    {
        try
        {
            int value = statesCensusAnalyzer.checkNumberOfRecords();
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
            int value = statesCensusAnalyzer.checkNumberOfRecords();
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

                int value = statesCensusAnalyzer.checkNumberOfRecords();
                Assert.assertEquals(37, value);
            }
             catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_FIELD,e.type);
        }

    }

    @Test
    public void givenMethod_ifFoundNoHeader_ShouldReturnException()
    {
        try {

            int value = statesCensusAnalyzer.checkNumberOfRecords();
            Assert.assertEquals(37, value);
        }
        catch (CSVFileException e)
        {
            System.out.println(e.getMessage());
            Assert.assertEquals(CSVFileException.ExceptionType.NO_SUCH_HEADER,e.type);
        }

    }

}
