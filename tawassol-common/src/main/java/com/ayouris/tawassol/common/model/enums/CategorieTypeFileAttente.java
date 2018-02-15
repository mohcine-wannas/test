package com.ayouris.tawassol.common.model.enums;

public enum CategorieTypeFileAttente {

	LIVRAISON_CONDITIONNE("Livraison conditionné"),
	LIVRAISON_VRAC( "Livraison vrac"),
	RECEPTION_VRAC("Réception vrac");
	
    private String key;

    private CategorieTypeFileAttente(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
