package ma.salamgaz.gwic.common.model.entity.generic;

import ma.salamgaz.gwic.admin.model.entity.Organization;

public interface LoginProvider {

    public String getCurrentLogin();

    public Organization getCurrentOrganization();

    public String getCurrentLang();

}
