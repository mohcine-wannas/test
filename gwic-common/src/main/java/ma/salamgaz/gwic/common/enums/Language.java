package ma.salamgaz.gwic.common.enums;

public enum Language {

    EN("en"),
    FR("fr-bj"),
    LA("la");

    private String lang;

    private Language(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

}
