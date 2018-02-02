package ma.salamgaz.gwic.common.model.entity.generic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Setter;

@Setter
@MappedSuperclass
@EntityListeners(value = { AuditEntityListener.class })
public abstract class AuditEntity extends BaseEntity {

    private static final long serialVersionUID = -1775849166653256400L;

    private String createdBy;

    private Date createdOn;

    private String updatedBy;

    private Date updatedOn;

    @Column(name = "created_by",updatable=false)
    public String getCreatedBy() {
        return createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on",updatable=false)
    public Date getCreatedOn() {
        return createdOn;
    }

    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    public Date getUpdatedOn() {
        return updatedOn;
    }

}
