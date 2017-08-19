package com.ayouris.tawassol.common.model.enums;

public enum StatutVentilation {

	ANNULEE("Annulée"),
	VALIDEE("Validée");

    private String key;

    private StatutVentilation(String value) {

        key = value;
    }

    public String getKey() {
        return key;
    }
}
