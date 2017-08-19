package com.ayouris.tawassol.common.model.enums;

public enum StatutEmplissage {

	EN_INSTANCE("En instance"),
	VALIDE("validé"),
	ANNULE("Annulé");
	
    private String key;

    private StatutEmplissage(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
