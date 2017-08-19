package com.ayouris.tawassol.common.model.entity;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */
@Setter
@Entity
@Table(name = "motif", schema = "tawassol")
public class Motif extends AuditEntity {

    private static final long serialVersionUID = 1475522145871L;
    private Long code;
    private String libelle;

    public Long getCode() {
        return code;
    }
    public String getLibelle() {
        return libelle;
    }

}
