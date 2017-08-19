package com.ayouris.tawassol.common.model.entity;

import lombok.Setter;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.Objet;

import javax.persistence.*;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
@Setter
@Entity
@Table(name = "circuitDerogation", schema = "tawassol", uniqueConstraints= @UniqueConstraint(columnNames={"SITE_ID", "objet"}))
public class CircuitDerogation extends AuditEntity {

    private static final long serialVersionUID = 1554668L;

    private Site site ;

    private Objet objet ;

    private User user;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    public Objet getObjet() {
        return objet;
    }

    public Boolean getActive(){
        return active;
    }

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SITE_ID"))
    public Site getSite() {
        return site;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ID"))
    public User getUser() {
        return user;
    }
}
