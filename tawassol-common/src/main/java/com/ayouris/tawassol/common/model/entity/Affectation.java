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
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "affectation", schema = "tawassol")
public class Affectation extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176024974860812684L;

	@NotNull
	private Client client;
	@NotNull
	private Site site;
	@NotNull
	private Produit produit;
	private Concessionnaire concessionnaire;
	private Boolean droitAvoir;
	private Boolean droitBCRG;
	private Boolean limiter;
	private Date dateDebut;
	private Date dateFin;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECT_CONCESS_ID"))  
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}
	
	@ManyToOne
	@JoinColumn(name = "produit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECT_PRODUIT_ID"))  
	public Produit getProduit() {
		return produit;
	}
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECT_CLIENT_ID"))  
	public Client getClient() {
		return client;
	}
	
	@ManyToOne
	@JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECT_SITE_ID"))  
	public Site getSite() {
		return site;
	}

	public Boolean getDroitAvoir() {
		return droitAvoir;
	}

	public Boolean getDroitBCRG() {
		return droitBCRG;
	}

	public Boolean getLimiter() {
		return limiter;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	
}
