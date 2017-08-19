package com.ayouris.tawassol.common.model.enums;

public enum TypeOperation {

	RECTIFICATION("Réctification	"),
	ANNULATION("Annulation");
	
    private String key;

    private TypeOperation(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
