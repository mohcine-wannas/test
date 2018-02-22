package com.ayouris.tawassol.common.model.enumeration;


import com.ayouris.tawassol.common.model.base.BaseEnum;

public enum SmsError implements BaseEnum {

    SUCCESS(0), AUTHENTIFICATION_FAILED(1), PARAMETERS_PROBLEM(2), SMSUSER_NOT_FOUNDED(3), NUM_LOT_ALREADY_EXIST(
            4), LOT_NOT_EXIST(5), NO_SMS_FOUNDED(6), PROBLEM_FORMATTING_SMS(7), SOLDE_NOT_FOUNDED(8), OTHER(9);

    private final int key;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    SmsError(int key) {
        this.key = key;
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||| Methodes utiles  |||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    public int getKey() {
        return key;
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String getDisplayText() {
        return getName();
    }

}
