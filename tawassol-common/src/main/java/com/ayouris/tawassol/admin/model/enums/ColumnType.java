package com.ayouris.tawassol.admin.model.enums;

public enum ColumnType {

	TEXT("TEXT"),
	CHECKBOX("CHECKBOX"),
	DATE("DATE"),
	SELECT("SELECT"),
	NUMBER("NUMBER"),
	ENUM("ENUM"),
	DATE_TIME("DATE TIME"),
	TIME("TIME");
	
    private String key;

    private ColumnType(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
	
}
