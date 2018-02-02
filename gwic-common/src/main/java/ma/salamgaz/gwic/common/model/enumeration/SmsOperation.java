package ma.salamgaz.gwic.common.model.enumeration;

import ma.salamgaz.gwic.common.model.base.BaseEnum;

public enum SmsOperation implements BaseEnum {

    CREDIT("enum.smsOperation.credit"), DEBIT("enum.smsOperation.debit");

    private final String key;

    SmsOperation(String key) {
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

        return key;
    }

    @Override
    public String toString() {

        return getDisplayText();
    }
}
