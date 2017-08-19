package com.ayouris.tawassol.common.model.entity.generic;

import com.ayouris.tawassol.admin.model.entity.Organization;

public interface LoginProvider {

    public String getCurrentLogin();

    public Organization getCurrentOrganization();

    public String getCurrentLang();

}
