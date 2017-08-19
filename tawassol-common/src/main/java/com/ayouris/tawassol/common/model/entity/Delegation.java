package com.ayouris.tawassol.common.model.entity;

import lombok.Setter;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.Objet;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Issmahane EL BAZ on 13/07/2017.
 */
@Setter
@Entity
@Table(name = "delegation", schema = "tawassol")
public class Delegation extends AuditEntity {

    private static final long serialVersionUID = 6176021274860812684L;

    private Objet objet;
    private User user;
    private User delegator;
    private Date du;
    private Date au;

    @Enumerated(EnumType.STRING)
    public Objet getObjet() {
        return objet;
    }

    public Date getDu(){
        return du;
    }
    public Date getAu(){
        return au;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_DELEG_ID"))
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "deleg_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DELEG_DELEG_ID"))
    public User getDelegator() {
        return delegator;
    }

}
