package ma.salamgaz.tawassol.common.model.enums;

public enum TypeCamion {
	
	SEMI_REMORQUE("Butane"),
	REMORQUE("Propane" );

    private String key;

    private TypeCamion(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
}
