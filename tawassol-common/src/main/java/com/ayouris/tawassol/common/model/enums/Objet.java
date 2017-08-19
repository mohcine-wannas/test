package com.ayouris.tawassol.common.model.enums;

/**
 * Created by Issmahane EL BAZ on 10/07/2017.
 */
public enum Objet {

    CONDITION_COMMERCIALES("Condition commerciales"),
    CITERNE_REEPREUVE("Citerne reepreuve");
    private String key;
    private Objet(String value) {

        key = value;
    }
    public String getKey() {
        return key;
    }
}
