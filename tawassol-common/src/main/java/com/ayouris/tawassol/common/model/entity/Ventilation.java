package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutVentilation;

@Setter
@Entity
@Table(name = "ventilation", schema = "tawassol")
public class Ventilation extends AuditEntity {

    private static final long serialVersionUID = 386494474478942641L;

    @NotNull
    private Double montant;
    
    @NotNull
    private StatutVentilation statut;
    
    @NotNull
    PieceReglement pieceReglement;
    
    @NotNull
    BonLivraison bonLivraison;
    
    
    @Enumerated(EnumType.STRING)
    public StatutVentilation getStatut() {
        return statut;
    }


	public Double getMontant() {
		return montant;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pieceReglement_id", nullable = true, foreignKey = @ForeignKey(name = "FK_VENTILATION_PR_ID"))
    public PieceReglement getPieceReglement() {
		return pieceReglement;
	}
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonLivraison_id", nullable = true, foreignKey = @ForeignKey(name = "FK_VENTILATION_BL_ID"))
    public BonLivraison getBonLivraison() {
		return bonLivraison;
	}
}