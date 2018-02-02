package ma.salamgaz.tawassol.common.model.entity.generic;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.hibernate.proxy.HibernateProxy;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.repository.util.PersistenceUtils;

@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5223025117869613136L;

    private Long id;
    /**
     * Returns the identifier of the entity.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tawassol_seq_generator")
    @SequenceGenerator(name = "tawassol_seq_generator", initialValue = 1, allocationSize = 100, sequenceName = "tawassol.tawassol_seq")
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        // unproxify if objects are Hibernate Proxy
        Object thisObj = (this instanceof HibernateProxy) ? PersistenceUtils.unproxifyEntity(this) : this;
        Object otherObj = (obj instanceof HibernateProxy) ? PersistenceUtils.unproxifyEntity(obj) : obj;

        if (this.id == null || obj == null || !(thisObj.getClass().equals(otherObj.getClass()))) {
            return false;
        }

        return this.id.equals(((BaseEntity) obj).getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + " (id: " + id + ")";
    }


}
