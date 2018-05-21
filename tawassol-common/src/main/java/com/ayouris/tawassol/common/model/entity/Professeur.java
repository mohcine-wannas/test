package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Entity
@Table(name = "professeur")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professeur extends User {

    private String fullname;

    private AffectationCycle affectationCycle;

    private List<AffectationUniteProf> affectationsUniteProf;

    private List<AffectationNiveauClasseProf> affectationsNiveauClasseProf;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "professeur")
    public List<AffectationUniteProf> getAffectationsUniteProf() {
        return affectationsUniteProf;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "professeur")
    public List<AffectationNiveauClasseProf> getAffectationsNiveauClasseProf() {
        return affectationsNiveauClasseProf;
    }


    @ManyToOne
    @JoinColumn(name = "affectation_cycle_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PROF_AC_ID"))
    public AffectationCycle getAffectationCycle() {
        return affectationCycle;
    }

    @Transient
    public String getFullname() {
        return getFirstname() + " " + getLastname();
    }
}
