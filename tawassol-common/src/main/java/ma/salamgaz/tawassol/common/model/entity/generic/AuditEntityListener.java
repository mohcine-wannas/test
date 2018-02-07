package ma.salamgaz.tawassol.common.model.entity.generic;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditEntityListener {

    private static LoginProvider loginProvider;

    @PrePersist
    public void prePersist(AuditEntity item) {
        if (item != null) {
            if (loginProvider != null) {
                item.setCreatedBy(loginProvider.getCurrentLogin());
            }
            item.setCreatedOn(new Date());
        }
    }

    @PreUpdate
    public void preUpdate(AuditEntity item) {
        if (item != null) {
            if (loginProvider != null) {
                item.setUpdatedBy(loginProvider.getCurrentLogin());
            }
            item.setUpdatedOn(new Date());
        }
    }

    public static void setLoginProvider(LoginProvider loginProvider2) {
        loginProvider = loginProvider2;
    }

}
