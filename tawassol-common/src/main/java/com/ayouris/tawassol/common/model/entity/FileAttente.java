package com.ayouris.tawassol.common.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutFileAttente;


@Setter
@Entity
@Table(name = "filleAttente", schema = "tawassol")
public class FileAttente extends AuditEntity {

	private static final long serialVersionUID = 1L;

	private String numBC;
	
	private String numOL;
	
	@NotNull
	private Date heureArrivee;
	
//	@NotNull
//	private Date tempsAttente;
	
	@NotNull
	private StatutFileAttente statut;
	
	@NotNull
	private Client client;
	
	@NotNull
	private Site site;
	
	@NotNull
	private TypeFileAttente typeFileAttente;
	@NotNull
	private Concessionnaire concessionnaire;
	
	@NotNull
	private Chauffeur chauffeur;
	
	@NotNull
	private Camion camion;
	
	private List<LigneFileAttente> ligneFileAttente;
	
	private Citerne citerne;
	
	private List<ControleCamionConformity>  controleCamionConformities;

	public String getNumBC() {
		return numBC;
	}

	public Date getHeureArrivee() {
		return heureArrivee;
	}

//	public Date getTempsAttente() {
//		return tempsAttente;
//	}

	@Enumerated(EnumType.STRING)
	public StatutFileAttente getStatut() {
		return statut;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_CLIENT_ID"))
	public Client getClient() {
		return client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_SITE_ID"))
	public Site getSite() {
		return site;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeFileAttente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_TYPEFILEATT_ID"))
	public TypeFileAttente getTypeFileAttente() {
		return typeFileAttente;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_CONC_ID"))
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chauffeur_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_CHAUFFEUR_ID"))
	public Chauffeur getChauffeur() {
		return chauffeur;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "camion_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_CAMION_ID"))
	public Camion getCamion() {
		return camion;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval= true)
	@JoinColumn(name = "fileattente_id")
	public List<LigneFileAttente> getLigneFileAttente() {
		return ligneFileAttente;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "fileattente_id")
	public List<ControleCamionConformity> getControleCamionConformities() {
		return controleCamionConformities;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citerne_id", nullable = true, foreignKey = @ForeignKey(name = "FK_FILEATTENTE_CITERNE_ID"))
	public Citerne getCiterne() {
		return citerne;
	}

	public String getNumOL() {
		return numOL;
	}

}
