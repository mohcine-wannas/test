package ma.salamgaz.tawassol.common.model.enums;

public enum TypeChargement {

	VRAC("Vrac"),
	CONDITIONNE("Conditionné");
	
    private String key;

    private TypeChargement(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
