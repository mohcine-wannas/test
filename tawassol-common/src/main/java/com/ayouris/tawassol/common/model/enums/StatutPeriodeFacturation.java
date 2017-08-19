package com.ayouris.tawassol.common.model.enums;

public enum StatutPeriodeFacturation {

	VALIDE("Validé"),
	EN_ISTANCE( "En instance");
	
	   private String key;

    private StatutPeriodeFacturation(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}