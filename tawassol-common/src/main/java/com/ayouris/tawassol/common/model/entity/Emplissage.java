package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.jdo.annotations.Unique;
import javax.persistence.CascadeType;
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
import com.ayouris.tawassol.common.model.enums.OrigineEmplissage;
import com.ayouris.tawassol.common.model.enums.StatutEmplissage;


@Setter
@Entity
@Table(name = "emplissage", schema = "tawassol")
public class Emplissage extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Unique
	@NotNull
	private String numOE;
	
	@NotNull
	private Integer qteEmplissage;
	
	@NotNull
	private StatutEmplissage statut;
	
	@NotNull
	private OrigineEmplissage origine;

	@NotNull
	private Client client;
	
	@NotNull
	private Concessionnaire concessionnaire;

	@NotNull
	private Produit produit;
	
	@NotNull
	private Site site;
	
	private FicheMarche ficheMarche;
	
	private Date heureDebut;
	
	private Date heureFin;
	
	private Integer qteBDLavee;
	
	private Double cadence;
	
	private Integer bdControle;
	
	private Integer bdDefect;
	
	private Integer bdAReparer;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EMP_SITE_ID"))
	public Site getSite() {
		return site;
	}
	
	public String getNumOE() {
		return numOE;
	}

	public Integer getQteEmplissage() {
		return qteEmplissage;
	}

	@Enumerated(EnumType.STRING)
	public StatutEmplissage getStatut() {
		return statut;
	}

	@Enumerated(EnumType.STRING)
	public OrigineEmplissage getOrigine() {
		return origine;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EMP_CLIENT_ID"))
	public Client getClient() {
		return client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EMP_CONC_ID"))
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EMP_PRODUIT_ID"))
	public Produit getProduit() {
		return produit;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ficheMarche_id", nullable = true, foreignKey = @ForeignKey(name = "FK_EMP_FICHE_ID"))
	public FicheMarche getFicheMarche() {
		return ficheMarche;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public Integer getQteBDLavee() {
		return qteBDLavee;
	}

	public Double getCadence() {
		return cadence;
	}

	public Integer getBdControle() {
		return bdControle;
	}

	public Integer getBdDefect() {
		return bdDefect;
	}

	public Integer getBdAReparer() {
		return bdAReparer;
	}
	


}
