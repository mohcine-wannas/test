package com.ayouris.tawassol.common.model.enums;

public enum TypeBon {

	BON_COMMANDE("Bon de commande"),
	BCRG( "BCRG");
	
	   private String key;

    private TypeBon(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}