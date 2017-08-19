package com.ayouris.tawassol.common.model.enums;

public enum StatutOrdreLivraison {

	EN_INSTANCE("En instance"),
	ANNULE("Annulé"),
	A_RECTIFIER("A Rectifier"),
	EN_TRAITEMENT("En Traitement"),
	VALIDE("Validé");
	
    private String key;

    private StatutOrdreLivraison(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
