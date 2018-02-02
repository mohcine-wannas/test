package ma.salamgaz.tawassol.common.enums;

/**
 * Message type exchanged by dealer and filling centers <br>
 * <i>INTERNAL</i> messages exchanged only by filling centers, <br>
 * <i>EXTERNAL</i> messages exchanged by dealer and filling centers. 
 *
 */
public enum MessageSubject {

	MISSING_INFORMATION, 
	MISSING_REQUIRED_DOCUMENT, 
	WRONG_PRODUCT_DESCRIPTION,
	OTHER

}