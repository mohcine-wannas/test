package ma.salamgaz.tawassol.admin.model.enums;


import lombok.Getter;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;

@Getter
public enum OrganizationAttribute {

    ACRONYM("QFillingCenter.fillingCenter.acronym"),
    AGREEMENT_NUMBER("QDelear.delear.agreementNumber");

    private String path;
    //private StringPath path;

    private ErrorMessageType errorMessageType;

    public ErrorMessageType getErrorMessageType() {
		return errorMessageType;
	}

	private OrganizationAttribute(String path) {
        this.path = path;
        //this.errorMessageType = errorMessageType;
    }
   
}
