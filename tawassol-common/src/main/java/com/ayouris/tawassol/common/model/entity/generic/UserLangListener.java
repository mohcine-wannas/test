package com.ayouris.tawassol.common.model.entity.generic;

public class UserLangListener {

    private static LoginProvider loginProvider;

    public static String getCurrentLang() {

        if (loginProvider != null) {
            return loginProvider.getCurrentLang();
        }
        return null;
    }

    public static void setLoginProvider(LoginProvider loginProvider2) {
        loginProvider = loginProvider2;
    }

}
