package com.ayouris.tawassol.common.model.enums;

public enum Periodicite {

	POUR_CHAQUE_ENLEVEMENT("Pour chaque enlèvement"),
	HEBDOMADAIRE("Hebdomadaire"),
	MENSUELLE("Mensuelle"),
	TOUS_LES("Tous les");
	
    private String key;

    private Periodicite(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
