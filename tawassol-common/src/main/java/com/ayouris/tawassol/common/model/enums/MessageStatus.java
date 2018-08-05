package com.ayouris.tawassol.common.model.enums;

public enum MessageStatus {
    EN_INSTANCE("En Instance"),
    VALIDE("Validée"),
    REJETE("Rejetée");

    private String key;

    MessageStatus(String value) {

        key = value;
    }

    public String getKey() {
        return key;
    }
}
