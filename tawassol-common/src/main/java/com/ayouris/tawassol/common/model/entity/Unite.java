package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "unite", schema = "tawassol")
public class Unite extends RefEntity {

    private String libelle;
    private Cycle cycle;

    public String getLibelle() {
        return libelle;
    }


    @ManyToOne
    @JoinColumn(name = "cycle_id", nullable = true, foreignKey = @ForeignKey(name = "FK_UNITE_CYCLE_ID"))
    public Cycle getCycle() {
        return cycle;
    }

}