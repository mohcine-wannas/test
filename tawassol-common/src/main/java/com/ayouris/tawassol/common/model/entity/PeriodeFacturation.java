package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutPeriodeFacturation;

@Setter
@Entity
@Table(name = "periodeFacturation", schema = "tawassol", uniqueConstraints = @UniqueConstraint(columnNames = { "mois_id",
		"annee_id" }))
public class PeriodeFacturation extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private StatutPeriodeFacturation statut;

	@NotNull
	private Mois mois;

	@NotNull
	private Annee annee;

	@Enumerated(EnumType.STRING)
	public StatutPeriodeFacturation getStatut() {
		return statut;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mois_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PF_MOIS_ID"))
	public Mois getMois() {
		return mois;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "annee_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PF_ANNEE_ID"))
	public Annee getAnnee() {
		return annee;
	}
}
