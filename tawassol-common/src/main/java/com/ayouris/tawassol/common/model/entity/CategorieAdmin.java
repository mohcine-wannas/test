package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Entity
@Table(name = "categorie_admin", schema = "tawassol")
public class CategorieAdmin extends RefEntity {

    private static final long serialVersionUID = 6589657722921185470L;

    private String libelle;

    public String getLibelle() {
        return libelle;
    }
}