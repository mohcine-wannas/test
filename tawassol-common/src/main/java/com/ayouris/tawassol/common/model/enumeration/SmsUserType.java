package com.ayouris.tawassol.common.model.enumeration;

import com.ayouris.tawassol.common.model.base.BaseEnum;

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
