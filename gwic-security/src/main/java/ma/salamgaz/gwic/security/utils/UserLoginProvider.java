package ma.salamgaz.gwic.security.utils;

import org.springframework.stereotype.Component;

import ma.salamgaz.gwic.admin.model.entity.Organization;
import ma.salamgaz.gwic.common.model.entity.generic.AuditEntityListener;
import ma.salamgaz.gwic.common.model.entity.generic.LoginProvider;
import ma.salamgaz.gwic.common.model.entity.generic.UserLangListener;

@Component
public class UserLoginProvider implements LoginProvider {

    public UserLoginProvider() {
        AuditEntityListener.setLoginProvider(this);
        UserLangListener.setLoginProvider(this);
    }

    @Override
    public String getCurrentLogin() {
        return SecurityUtils.getCurrentLogin();
    }

    @Override
    public Organization getCurrentOrganization() {
        return SecurityUtils.getCurrentUserOrganization();
    }

    @Override
    public String getCurrentLang() {
        return SecurityUtils.getCurrentUserLang();
    }

}
