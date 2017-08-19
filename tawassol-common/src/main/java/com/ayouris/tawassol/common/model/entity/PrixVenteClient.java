package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

@Setter
@Entity
@Table(name = "prixVenteClient", schema = "tawassol", uniqueConstraints = @UniqueConstraint(columnNames = { "site_id",
		"client_id", "produit_id", "dateDebut", "dateFin" }))
public class PrixVenteClient extends AuditEntity {

	private static final long serialVersionUID = 1L;

	private Client client;

	@NotNull
	private Long prix;

	@NotNull
	private Produit produit;

	private Site site;

	@NotNull
	@Column
	private Date dateDebut;

	@NotNull
	private Date dateFin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PRIX_SITE_ID"))
	public Site getSite() {
		return site;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PRIX_CLIENT_ID"))
	public Client getClient() {
		return client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PRIX_PRODUIT_ID"))
	public Produit getProduit() {
		return produit;
	}

	@Column(name = "dateDebut")
	public Date getDateDebut() {
		return dateDebut;
	}

	@Column(name = "dateFin")
	public Date getDateFin() {
		return dateFin;
	}
	@Column(name = "prix")
	public Long getPrix() {
		return prix;
	}
}
