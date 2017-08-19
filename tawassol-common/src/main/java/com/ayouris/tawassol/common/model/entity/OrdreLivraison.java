package com.ayouris.tawassol.common.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.TypeBon;
import com.ayouris.tawassol.common.model.enums.TypeChargement;


@Setter
@Entity
@Table(name = "ordreLivraison", schema = "tawassol")
public class OrdreLivraison extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String numBC;
	
	private String numOL;
	
	@NotNull
	private StatutOrdreLivraison statut;
	
	private TypeBon typeBon;
	
	@NotNull
	private TypeChargement typeChargement;
	
	@NotNull
	private Date dateCommande;
	
	@NotNull
	private Client client;
	
	@NotNull
	private Site site;
	
	private JourneeActivite journeeActivite;
	
	@NotNull
	private Concessionnaire concessionnaire;
	
	@NotNull
	private Chauffeur chauffeur;
	
	@NotNull
	private Camion camion;
	
	@NotNull
	private DestinationOL destination;
	
	private LigneOrdreLivraisonVrac ligneOrdreLivraisonVrac;
	
	private Set<LigneOrdreLivraison> ligneOrdreLivraison;
	
	private Citerne citerne;
	
	public String getNumBC() {
		return numBC;
	}

	@Enumerated(EnumType.STRING)
	public StatutOrdreLivraison getStatut() {
		return statut;
	}

	@Enumerated(EnumType.STRING)
	public TypeBon getTypeBon() {
		return typeBon;
	}
	
	@Enumerated(EnumType.STRING)
	public TypeChargement getTypeChargement() {
		return typeChargement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_CLIENT_ID"))
	public Client getClient() {
		return client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_SITE_ID"))
	public Site getSite() {
		return site;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_CONC_ID"))
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chauffeur_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_CHAUFFEUR_ID"))
	public Chauffeur getChauffeur() {
		return chauffeur;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "camion_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_CAMION_ID"))
	public Camion getCamion() {
		return camion;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OL_DESTINATION_ID"))
	public DestinationOL getDestination() {
		return destination;
	}

	public Date getDateCommande() {
		return dateCommande;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval= true)
	@JoinColumn(name = "orderlivraison_id")
	public Set<LigneOrdreLivraison> getLigneOrdreLivraison() {
		return ligneOrdreLivraison;
	}
	
	@Column(unique = true)
	public String getNumOL() {
		return numOL;
	}
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "ligneOLVrac_id", nullable = true, foreignKey = @ForeignKey(name = "FK_OL_LineVRAC_ID"))
	public LigneOrdreLivraisonVrac getLigneOrdreLivraisonVrac() {
		return ligneOrdreLivraisonVrac;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citerne_id", nullable = true, foreignKey = @ForeignKey(name = "FK_OL_CITERNE_ID"))
	public Citerne getCiterne() {
		return citerne;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journeeActivite_id", nullable = true, foreignKey = @ForeignKey(name = "FK_OL_JA_ID"))
	public JourneeActivite getJourneeActivite() {
		return journeeActivite;
	}
	

}
