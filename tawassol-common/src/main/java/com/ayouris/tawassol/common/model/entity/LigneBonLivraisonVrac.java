package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutLigneBonLivraison;

import java.util.Date;


@Setter
@Entity
@Table(name = "ligne_bl_vrac", schema = "tawassol")
public class LigneBonLivraisonVrac extends AuditEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Produit produit;

	private StatutLigneBonLivraison statut;

	private Integer qteCommandees;

	private Date heureDebutChargement;

	private Date heureFinChargement;

	private String nDePeseeDeSortie;

	private String nDePeseeDeEntree;

	private Integer poidsVide;

	private Integer poidsPleine;

	private Integer qteLivrees;

	public String getNDePeseeDeEntree(){ return nDePeseeDeEntree;}

	public String getNDePeseeDeSortie(){ return nDePeseeDeSortie;}

	public Date getHeureDebutChargement(){ return heureDebutChargement;}

	public Date getHeureFinChargement(){ return heureFinChargement;}

	public Integer getQteCommandees (){ return qteCommandees;}

	public Integer getPoidsVide (){ return poidsVide;}

	public Integer getPoidsPleine (){ return poidsPleine;}

	public Integer getQteLivrees (){ return qteLivrees;}
	
	@Enumerated(EnumType.STRING)
	public StatutLigneBonLivraison getStatut() {
		return statut;
	}

    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "produit_id", foreignKey = @ForeignKey(name = "FK_BL_VRAC_PROD_ID"))  
    public Produit getProduit() {
		return produit;
	}

	
}
