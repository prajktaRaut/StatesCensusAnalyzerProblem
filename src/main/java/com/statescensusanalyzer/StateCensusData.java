package com.statescensusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

    @CsvBindByName
    int SrNo;

    @CsvBindByName
    String StateName;

    @CsvBindByName
    String TIN;

    @CsvBindByName
    String StateCode;

    Double Population;

    Double AreaInSqKm;

    Double DensityPerSqKm;

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int srNo) {
        SrNo = srNo;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public Double getPopulation() {
        return Population;
    }

    public void setPopulation(Double population) {
        Population = population;
    }

    public Double getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(Double areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public Double getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    public void setDensityPerSqKm(Double densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }
}
