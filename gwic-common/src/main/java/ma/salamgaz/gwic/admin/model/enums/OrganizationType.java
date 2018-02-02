package ma.salamgaz.gwic.admin.model.enums;

import lombok.Getter;

@Getter
public enum OrganizationType {

	SIEGE("csv.list.organization.type.siege"),
	FILLING_CENTER("csv.list.organization.type.FILLING_STATION"),
    DEALER("csv.list.organization.type.DEALER"),
    CUSTOMER("csv.list.organization.type.CUSTOMS");

    private String key;

    private OrganizationType(String value) {
        key = value;
    }

}
