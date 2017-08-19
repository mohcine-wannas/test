package com.ayouris.tawassol.common.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutBonLivraison;
import com.ayouris.tawassol.common.model.enums.TypeChargement;

@Setter
@Entity
@Table(name = "bonLivraison", schema = "tawassol")
public class BonLivraison extends AuditEntity implements Comparable<BonLivraison> {

	private static final long serialVersionUID = 1L;

	@NotNull
	private StatutBonLivraison statut;
	private TypeChargement typeChargement;
	private String numBL;
	private String note;

	private Double montantRestant;
	private Double montantDu;
	private Boolean regle;

	@NotNull
	private OrdreLivraison ordreLivraison;

	private LigneBonLivraisonVrac ligneBonLivraisonVrac;

	private List<LigneBonLivraisonConditionne> ligneBonLivraisonConditionne;

	private List<ControleCamionBonLivraisonConformity> controleCamionConformities;

	public String getNote() {
		return note;
	}

	@Enumerated(EnumType.STRING)
	public StatutBonLivraison getStatut() {
		return statut;
	}

	@Enumerated(EnumType.STRING)
	public TypeChargement getTypeChargement() {
		return typeChargement;
	}

	public String getNumBL() {
		return numBL;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ordreLivraison_id", nullable = false, foreignKey = @ForeignKey(name = "FK_BL_OL_ID"))
	public OrdreLivraison getOrdreLivraison() {
		return ordreLivraison;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "bonlivraison_id")
	public List<LigneBonLivraisonConditionne> getLigneBonLivraisonConditionne() {
		return ligneBonLivraisonConditionne;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "ligneBLVrac_id", nullable = true, foreignKey = @ForeignKey(name = "FK_BL_LineVRAC_ID"))
	public LigneBonLivraisonVrac getLigneBonLivraisonVrac() {
		return ligneBonLivraisonVrac;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "bonlivraison_id")
	public List<ControleCamionBonLivraisonConformity> getControleCamionConformities() {
		return controleCamionConformities;
	}

	public Boolean getRegle() {
		return regle;
	}

	public Double getMontantRestant() {
		return montantRestant;
	}

	public Double getMontantDu() {
		return montantDu;
	}

	@Override
	public int compareTo(BonLivraison o) {
		return getCreatedOn().compareTo(o.getCreatedOn());
	}

}
