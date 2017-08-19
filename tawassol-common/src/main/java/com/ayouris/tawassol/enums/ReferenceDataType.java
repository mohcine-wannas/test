package com.ayouris.tawassol.enums;

import com.ayouris.tawassol.common.model.entity.lang.CountryLang;
import com.ayouris.tawassol.common.model.entity.ref.CountryRef;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;

@Getter
@SuppressWarnings("rawtypes")
public enum ReferenceDataType {
    
    COUNTRY("countries", CountryRef.class, CountryLang.class);
    
    private String context;
    private Class entityRef;
    private Class entityLang;
    
    private ReferenceDataType (String context, Class entityRef, Class entityLang) {
        this.context = context;
        this.entityRef = entityRef;
        this.entityLang = entityLang;
    }

    @JsonCreator
    public static ReferenceDataType fromValue(String context) {
        for (ReferenceDataType type : ReferenceDataType.values()) {
            if (type.context.equalsIgnoreCase(context)) {
                return type;
            }
        }
        return null;
    }
    
}
