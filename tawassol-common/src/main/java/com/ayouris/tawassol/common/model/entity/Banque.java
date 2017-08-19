package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by YounesM on 05/05/2017.
 */

@Entity
@Table(name = "banque", schema = "tawassol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Banque extends RefEntity{

    private static final long serialVersionUID = 6224385346631394122L;
    private String libelle;
    private String abreviation;
    private String adresse;
    private Boolean active;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
