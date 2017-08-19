package com.ayouris.tawassol.common.model.entity;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Issmahane EL BAZ on 17/07/2017.
 */@Setter
@Entity
@Table(name = "profil", schema = "tawassol")
public class Profil extends AuditEntity {

    private static final long serialVersionUID = 1472153145871L;
    private Long code;
    private String libelle;

    public Long getCode() {
        return code;
    }
    public String getLibelle() {
        return libelle;
    }

}

