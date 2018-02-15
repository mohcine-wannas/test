package ma.salamgaz.tawassol.admin.model.beans;

import java.io.Serializable;
import javax.ws.rs.QueryParam;
import org.apache.commons.lang3.StringUtils;

import ma.salamgaz.tawassol.admin.model.enums.OrganizationAttribute;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OrganizationAttributeBean implements Serializable {

    private static final long serialVersionUID = 2831013159028567845L;

    @QueryParam("organizationId")
    Long organizationId;

    @QueryParam("attributeName")
    OrganizationAttribute attributeName;

    @QueryParam("attributeValue")
    String attributeValue;


    public OrganizationAttributeBean() {
    }

    public OrganizationAttributeBean(Long organizationId, OrganizationAttribute attributeName, String attributeValue) {
        this.organizationId = organizationId;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }


    public boolean isNewOrganization() {
        return this.organizationId == null;
    }

    public boolean isNotBlank() {
        return StringUtils.isNotBlank(this.attributeValue);
    }
}
