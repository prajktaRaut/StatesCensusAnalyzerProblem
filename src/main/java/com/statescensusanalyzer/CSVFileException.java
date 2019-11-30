package com.statescensusanalyzer;

public class CSVFileException extends Exception {

    enum ExceptionType{

        NO_SUCH_FILE
    }

    ExceptionType type;

    public CSVFileException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }
}