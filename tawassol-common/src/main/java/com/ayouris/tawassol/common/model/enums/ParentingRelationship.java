package com.ayouris.tawassol.common.model.enums;

public enum ParentingRelationship {

	FATHER("Père"),
	MOTHER("Père"),
	BROTHER("Frère"),
	SISTER("Soeur");

    private String key;

    ParentingRelationship(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
