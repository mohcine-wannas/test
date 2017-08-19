package com.ayouris.tawassol.common.model.enums;

/**
 * Created by Issmahane EL BAZ on 19/07/2017.
 */
public enum TypePiece {

    CHEQUE_CERTIFIE("Chèque certifié");
    private String key;

    private TypePiece(String value) {

        key = value;
    }
    public String getKey() {
        return key;
    }
}
