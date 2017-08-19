package com.ayouris.tawassol.common.model.entity;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;


import javax.persistence.*;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */
@Setter
@Entity
@Table(name = "ligne_prestation", schema = "tawassol")
public class LignePrestation extends AuditEntity {

    private static final long serialVersionUID = 1475525871L;

    private Prestation prestation;
    private Integer qte;

    public Integer getQte() {
        return qte;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestation_id", foreignKey = @ForeignKey(name = "FK_LIGNE_PR_ID"))
    public Prestation getPrestation() {
        return prestation;
    }

}
