package ma.salamgaz.tawassol.common.model.entity.generic;

import ma.salamgaz.tawassol.admin.model.entity.Organization;

public interface LoginProvider {

    public String getCurrentLogin();

    public Organization getCurrentOrganization();

    public String getCurrentLang();

}
