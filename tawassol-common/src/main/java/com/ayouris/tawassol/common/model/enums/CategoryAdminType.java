package com.ayouris.tawassol.common.model.enums;

public enum CategoryAdminType {

    PROFESSEUR("Professeur"),
    PARENT("Parent");

    private String key;

    CategoryAdminType(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
