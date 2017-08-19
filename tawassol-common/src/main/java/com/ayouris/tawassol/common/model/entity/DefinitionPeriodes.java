package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

@Setter
@Entity
@Table(name = "definitionPeriodes", schema = "tawassol")
public class DefinitionPeriodes extends AuditEntity implements Comparable<DefinitionPeriodes>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2024894095679876415L;

	@NotNull
	private Date dateReglement;

	@NotNull
	private Date dateDebut;
	
	@NotNull
	private Date dateFin;

	@NotNull
	private Concessionnaire concessionnaire;

	private Date dateTolerance;

	protected Date getDateReglement() {
		return dateReglement;
	}

	protected Date getDateTolerance() {
		return dateTolerance;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DEFIPERIODE_CONCESS_ID"))
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	
	@Override
	public int compareTo(DefinitionPeriodes o) {
		return getDateReglement().compareTo(o.getDateReglement());
	}
}
