package com.ayouris.tawassol.common.model.enums;

/**
 * Created by Issmahane EL BAZ on 19/07/2017.
 */
public enum StatutPieceReglement {

	EN_INSTANCE("En instance"),
	ANNULEE("Annulée"),
	VALIDEE("Validée");

    private String key;

    private StatutPieceReglement(String value) {

        key = value;
    }

    public String getKey() {
        return key;
    }
}
