package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "affectation_niveau_classe_prof", schema = "tawassol")
public class AffectationNiveauClasseProf extends BaseEntity {

    private Professeur professeur;
    private Niveau niveau;
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "prof_id", nullable = true, foreignKey = @ForeignKey(name = "FK_ANCP_PROF_ID"))
    public Professeur getProfesseur() {
        return professeur;
    }

    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ANCP_NIVEAU_ID"))
    public Niveau getNiveau() {
        return niveau;
    }

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ANCP_CLASSE_ID"))
    public Classe getClasse() {
        return classe;
    }
}
