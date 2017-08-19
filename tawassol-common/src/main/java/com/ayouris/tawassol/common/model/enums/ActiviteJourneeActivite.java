package com.ayouris.tawassol.common.model.enums;

public enum ActiviteJourneeActivite {

	LIVRAISON("Livraison"),
	RDG("Retour de gaz"),
	EMPLISSAGE("Emplissage");
	
    private String key;

    private ActiviteJourneeActivite(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
