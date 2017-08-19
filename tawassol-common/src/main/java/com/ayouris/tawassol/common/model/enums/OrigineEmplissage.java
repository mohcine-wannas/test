package com.ayouris.tawassol.common.model.enums;

public enum OrigineEmplissage {

	CREUX("Creux"),
	LIVRAISON("Livraison");
	
    private String key;

    private OrigineEmplissage(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
