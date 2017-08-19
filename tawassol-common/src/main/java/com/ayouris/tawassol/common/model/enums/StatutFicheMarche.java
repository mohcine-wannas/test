package com.ayouris.tawassol.common.model.enums;

public enum StatutFicheMarche {

	EN_INSTANCE("En Instance"),
	VALIDE("Validée"),
	CLOTUREE( "Clôturée");
	
    private String key;

    private StatutFicheMarche(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
