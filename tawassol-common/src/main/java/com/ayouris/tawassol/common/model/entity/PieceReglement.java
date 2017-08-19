package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

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
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.common.model.enums.StatutPieceReglement;

/**
 * Created by Issmahane EL BAZ on 19/07/2017.
 */
@Setter
@Entity
@Table(name = "pieceReglement", schema = "tawassol")
public class PieceReglement extends AuditEntity implements Comparable<PieceReglement> {

    private static final long serialVersionUID = 386494474478942641L;

    @NotNull
    private Banque banque;
    @NotNull
    private Double montant;
    @NotNull
    private Concessionnaire concessionnaire;
    @NotNull
    private String reference;
    @NotNull
    private StatutPieceReglement statut;
    @NotNull
    private TypePieceReglement typePieceReglement;
    @NotNull
    private Periodicite periodicite;
    @NotNull
    private Date dateReglement;
    private boolean soldee;
    @NotNull
    private Double montantRestant;
    @NotNull
    private Double montantDu;
    
    private String note;
    private Date dateEligible;
    private Date periodeCouverteDebut;
    private Date periodeCouverteFin;
    private String pj;
    
    
    @Enumerated(EnumType.STRING)
    public StatutPieceReglement getStatut() {
        return statut;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typePieceReglement_id", nullable = true, foreignKey = @ForeignKey(name = "FK_REGL_TYPEREG_ID"))
    public TypePieceReglement getTypePieceReglement() {
        return typePieceReglement;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concessionnaire_id", nullable = true, foreignKey = @ForeignKey(name = "FK_REGL_CONCESS_ID"))
    public Concessionnaire getConcessionnaire() {
        return concessionnaire;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque_id", nullable = true, foreignKey = @ForeignKey(name = "FK_REGL_Banque_ID"))
    public Banque getBanque(){
        return banque;
    }

    public String getPj(){
        return pj;
    }

    public Date getDateReglement(){
        return dateReglement;
    }

    public Date getDateEligible(){
        return dateEligible;
    }

    public boolean getSoldee(){
        return soldee;
    }

    public Double getMontant(){
        return montant;
    }

    public Double getMontantRestant(){
        return montantRestant;
    }

    public String getReference(){
        return reference;
    }

    public String getNote(){
        return note;
    }
	public Date getPeriodeCouverteDebut() {
		return periodeCouverteDebut;
	}
	public Date getPeriodeCouverteFin() {
		return periodeCouverteFin;
	}
	@Enumerated(EnumType.STRING)
	public Periodicite getPeriodicite() {
		return periodicite;
	}

	public Double getMontantDu() {
		return montantDu;
	}
	
	@Override
	public int compareTo(PieceReglement o) {
		return getDateReglement().compareTo(o.getDateReglement());
	}
	
}