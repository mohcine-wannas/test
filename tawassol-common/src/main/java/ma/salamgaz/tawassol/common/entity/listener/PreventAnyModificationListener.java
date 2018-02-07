package ma.salamgaz.tawassol.common.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import ma.salamgaz.tawassol.common.entity.exception.RefEntityModificationException;

/**
 * Prevent any modification on entities having this listener
 * 
 * @author anis.gharrabia
 *
 */
public class PreventAnyModificationListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    public void preventEntityModification(Object entity) {
        throw new RefEntityModificationException(
                String.format("Modification on the ref entity [%1$s] is not allowed.", entity.getClass()));
    }
}
