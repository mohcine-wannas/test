package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "affectation_unite_prof", schema = "tawassol")
public class AffectationUniteProf extends BaseEntity {

    private Unite unite;
    private Professeur professeur;
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "unite_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AUP_unite_ID"))
    public Unite getUnite() {
        return unite;
    }

    @ManyToOne
    @JoinColumn(name = "prof_id", nullable = true, foreignKey = @ForeignKey(name = "FK_AUP_PROF_ID"))
    public Professeur getProfesseur() {
        return professeur;
    }


    public Boolean getEnabled() {
        return enabled;
    }
}
