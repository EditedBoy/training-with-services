package com.util.enums;

public enum DataBaseProperties {

    PRIMARY_KEY("PRIMARY KEY"),
    FOREIGN_KEY("FOREIGN KEY"),

    AUTO_INCEMENT("AUTO_INCREMENT"),
    NOT_NULL("NOT NULL"),

    TEXT("TEXT"),
    INT("INT"),
    BOOLEAN("BOOLEAN");

    private String property;

    DataBaseProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
