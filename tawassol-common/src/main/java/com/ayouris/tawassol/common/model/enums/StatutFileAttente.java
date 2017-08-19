package com.ayouris.tawassol.common.model.enums;

public enum StatutFileAttente {

	AU_PARKING("Au parking"),
	A_RECTIFIER("A Rectifier"),
	A_ENTREE("A l'entrée"),
	AU_CENTRE( "Au centre"),
	SORTIE("Sortie"),
	A_LA_SORTIE("À la sortie"),
	PROVISOIRE("Provisoire"),
	ANNULE("Annulé");
	
    private String key;

    private StatutFileAttente(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}
