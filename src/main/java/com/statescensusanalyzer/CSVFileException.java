package com.statescensusanalyzer;

public class CSVFileException extends Exception {

    public enum ExceptionType{

        NO_SUCH_FILE,NO_SUCH_FIELD,NO_SUCH_HEADER,INVALID_DELIMITER
    }

    public ExceptionType type;

    public CSVFileException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }
}
