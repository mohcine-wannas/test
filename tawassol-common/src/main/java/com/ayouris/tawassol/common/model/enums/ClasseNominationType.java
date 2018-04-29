package com.ayouris.tawassol.common.model.enums;

public enum ClasseNominationType {

	ALPHABETIQUE("Alphabethique"),
	NUMERIQUE( "Numerique");
	
    private String key;

    ClasseNominationType(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
