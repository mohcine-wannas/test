package com.ayouris.tawassol.common.model.enums;

public enum NatureProduit {
	
	BUTANE("Butane"),PROPANE("Propane");

    private String key;

    private NatureProduit(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
    
}
