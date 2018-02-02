package ma.salamgaz.gwic.common.model.enumeration;

import ma.salamgaz.gwic.common.model.base.BaseEnum;

public enum ProfilType implements BaseEnum {
    UTILISATEUR("enum.userType.utilisateur"), ADMINISTRATEUR("enum.userType.administrateur"), INVITE("enum.userType.invite");

    private final String key;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    ProfilType(String key) {
        this.key = key;
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||| Methodes utiles  |||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    public String getKey() {
        return key;
    }

    public String getName() {
        return this.name();
    }


    @Override
    public String getDisplayText() {
        return key;
    }

    @Override
    public String toString() {
        return getDisplayText();
    }

}
