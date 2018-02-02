package ma.salamgaz.tawassol.common.model.enumeration;


/**
 * ENUM_TYPE_ACTION
 *
 * @author JAF
 * @version 1.2
 */
public enum ENUM_TYPE_ACTION {

		ADD,
		UPDATE,
		DELETE;
	
	public ENUM_TYPE_ACTION getEnumValue() {
		return this;
	}
	
	public Integer getValue() {
		return this.ordinal();
	}

}