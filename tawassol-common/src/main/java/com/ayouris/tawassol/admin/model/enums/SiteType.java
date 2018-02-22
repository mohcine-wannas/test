package com.ayouris.tawassol.admin.model.enums;

public enum SiteType {

	CENTRE("Centre"),
	TERMINALE("Terminale"),
	SIEGE("Siege");
	
    private String key;

    private SiteType(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
	
}
