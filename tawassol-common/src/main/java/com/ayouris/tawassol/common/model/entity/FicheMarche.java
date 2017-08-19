package com.ayouris.tawassol.common.model.entity;

import java.util.Date;
import java.util.Set;

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
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutFicheMarche;

@Setter
@Entity
@Table(name = "ficheMarche", schema = "tawassol")
public class FicheMarche extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private StatutFicheMarche statut;

	@NotNull
	private Date dateJournee;

	private Date tempsPause;

	private Long qteEmplissageTotal;

	private Date tempsEffectif;

	private Double cadenceHorsArrets;

	private Double cadenceArretsCompris;
	
	private Set<ArretEmplissage> arretsEmplissage;

	private Set<CreuxEmplissage> creuxEmplissage;
	
	private Date tempsCreux;

	private Date tempsArrets;

	@NotNull
	private Produit produit;

	@NotNull
	private User user;

	@NotNull
	private Post post;
	
	private JourneeActivite journeeActivite;

	@Enumerated(EnumType.STRING)
	public StatutFicheMarche getStatut() {
		return statut;
	}

	public Date getDateJournee() {
		return dateJournee;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval= true)
	@JoinColumn(name = "ficheMarche_id")
	public Set<ArretEmplissage> getArretsEmplissage() {
		return arretsEmplissage;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval= true)
	@JoinColumn(name = "ficheJouMarche_id")
	public Set<CreuxEmplissage> getCreuxEmplissage() {
		return creuxEmplissage;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id", nullable = true, foreignKey = @ForeignKey(name = "FK_FM_POST_ID"))
	public Post getPost() {
		return post;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produit_id", nullable = true, foreignKey = @ForeignKey(name = "FK_FM_PRODUIT_ID"))
	public Produit getProduit() {
		return produit;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(name = "FK_FM_USER_ID"))
	public User getUser() {
		return user;
	}

	public Long getQteEmplissageTotal() {
		return qteEmplissageTotal;
	}

	public Double getCadenceArretsCompris() {
		return cadenceArretsCompris;
	}

	public Date getTempsPause() {
		return tempsPause;
	}

	public Date getTempsEffectif() {
		return tempsEffectif;
	}

	public Double getCadenceHorsArrets() {
		return cadenceHorsArrets;
	}

	public Date getTempsCreux() {
		return tempsCreux;
	}

	public Date getTempsArrets() {
		return tempsArrets;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journeeActivite_id", nullable = true, foreignKey = @ForeignKey(name = "FK_FM_JA_ID"))
	public JourneeActivite getJourneeActivite() {
		return journeeActivite;
	}

	

}
