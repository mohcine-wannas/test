package com.ayouris.tawassol.common.model.enums;

public enum StatutJourneeActivite {

	OUVERTE("Ouverte"),
	CLOTUREE( "Clôturée");
	
    private String key;

    private StatutJourneeActivite(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
