package ma.salamgaz.gwic.common.model.enumeration;

import ma.salamgaz.gwic.common.model.base.BaseEnum;

public enum SmsLotEtat implements BaseEnum {

    ENCOURS_ENVOI("enum.lotSmsEtat.encoursEnvoi"), PLANIFIE("enum.lotSmsEtat.planifie"), ENVOYE(
            "enum.lotSmsEtat.envoye");

    private final String key;

    SmsLotEtat(String key) {
        this.key = key;
    }

    public String getKey() {

        return key;
    }

    public String getName() {

        return this.name();
    }

    @Override
    public String getDisplayText() {

        return getName();
    }

    @Override
    public String toString() {

        return getDisplayText();
    }
}
