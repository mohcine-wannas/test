package ma.salamgaz.gwic.common.model.enumeration;

import ma.salamgaz.gwic.common.model.base.BaseEnum;

public enum RoleType implements BaseEnum {
    PAGE("cst.roleType.page"), ACTION("cst.roleType.action"), RAPPORT("cst.roleType.rapport"), MODULE("cst.roleType.module");

    private final String key;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    RoleType(String key) {
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
