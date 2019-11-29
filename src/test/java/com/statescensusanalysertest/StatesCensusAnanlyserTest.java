package com.statescensusanalysertest;

import com.statescensusanalyzer.StatesCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StatesCensusAnanlyserTest {

    StatesCensusAnalyzer statesCensusAnalyzer=new StatesCensusAnalyzer();
    @Test
    public void givenMethod_CheckNumberOfRecordesMatchesOrNot_ShouldReturnTrue() throws IOException {

        int value=statesCensusAnalyzer.checkNumberOfRecords();
        Assert.assertEquals(37,value);

    }
}
