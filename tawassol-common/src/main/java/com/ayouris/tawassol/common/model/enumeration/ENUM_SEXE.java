package com.ayouris.tawassol.common.model.enumeration;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ENUM_SEXE
 *
 * @author JAF
 * @version 1.2
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ENUM_SEXE {

    M,
    F;

    public ENUM_SEXE getEnumValue() {
        return this;
    }

    public Integer getValue() {
        return this.ordinal();
    }

    @JsonValue
    public String getStringValue() {
        return this.toString();
    }

}