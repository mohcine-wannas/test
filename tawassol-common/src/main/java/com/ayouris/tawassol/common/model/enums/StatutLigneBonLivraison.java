package com.ayouris.tawassol.common.model.enums;

public enum StatutLigneBonLivraison {

	EN_INSTANCE("En instance"),
	ANNULE("Annulé"),
	EN_TRAITEMENT("En Traitement"),
    VALIDE("Validé"),
	MARQUER_TRAITE("Marqué traité");
	
    private String key;

    private StatutLigneBonLivraison(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
