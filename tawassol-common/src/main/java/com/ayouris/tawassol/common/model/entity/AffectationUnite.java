package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "affectation_unite", schema = "tawassol")
public class AffectationUnite extends BaseEntity {

    private Unite unite;
    private AffectationCycle affectationCycle;
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "unite_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AU_unite_ID"))
    public Unite getUnite() {
        return unite;
    }

    @ManyToOne
    @JoinColumn(name = "affectation_cycle_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AU_AC_ID"))
    public AffectationCycle getAffectationCycle() {
        return affectationCycle;
    }


    public Boolean getEnabled() {
        return enabled;
    }

}
