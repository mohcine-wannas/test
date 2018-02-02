package ma.salamgaz.gwic.common.model.enumeration;

import ma.salamgaz.gwic.common.model.base.BaseEnum;

public enum SmsUserType implements BaseEnum {

    NAWAT;

    public String getName() {
        return this.name();
    }

    @Override
    public String getDisplayText() {

        return this.name();
    }

}
