package ma.salamgaz.tawassol.common.model.enumeration;

import ma.salamgaz.tawassol.common.model.base.BaseEnum;

public enum SmsEtat implements BaseEnum {

    EN_ATTENTE, PLANIFIE, ENCOURS_ENVOI, ENVOYE, ECHEC_ENVOI, ACCUSE_RECEPTION, ECHEC_ACCUSE;

    public String getName() {
        return this.name();
    }

    @Override
    public String getDisplayText() {

        return this.name();
    }

}
