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
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutJourneeActivite;


@Setter
@Entity
@Table(name = "journeeActivite", schema = "tawassol")
public class JourneeActivite extends RefEntity {

	private static final long serialVersionUID = 1L;

	
	
	@NotNull
	private StatutJourneeActivite statut;
	
	@NotNull
	private ActiviteJourneeActivite activite;
	
	@NotNull
	private Date dateJournee;
	
	@NotNull
	private Date dateOuverture;
	
	private Date dateFermeture;
	
	@NotNull
	private Site site;
	

	@Enumerated(EnumType.STRING)
	public StatutJourneeActivite getStatut() {
		return statut;
	}
	
	@Enumerated(EnumType.STRING)
	public ActiviteJourneeActivite getActivite() {
		return activite;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_JA_SITE_ID"))
	public Site getSite() {
		return site;
	}

	public Date getDateJournee() {
		return dateJournee;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}
	public Date getDateFermeture() {
		return dateFermeture;
	}
	


}
