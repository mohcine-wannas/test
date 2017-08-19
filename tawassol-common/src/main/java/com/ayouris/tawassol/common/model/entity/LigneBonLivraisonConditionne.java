package com.ayouris.tawassol.common.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.StatutLigneBonLivraison;

import java.util.Date;
import java.util.List;


@Setter
@Entity
@Table(name = "ligne_bl_conditionne", schema = "tawassol")
public class LigneBonLivraisonConditionne extends AuditEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Produit produit;
	private BonLivraison bonLivraison;
    private List<LignePrestation> lignePrestation;
    private Motif motifManque;
	private StatutLigneBonLivraison statut;

	private Date heureDebutChargement;
	private Date heureFinChargement;
	private Integer qteDechargee;
	private Integer monchonDebut;
	private Integer monchonFin;
	private Integer retourneeEtrangere;
	private Integer retourneeSansChap;
	private Integer retourneeAutre;
    private Integer avecEchange;
    private Integer sansEchange;
    private Integer priseStockPlein;
    private Integer empliesSurChaine;
    private Integer qteBD;
    private Integer qteTM;
    private Integer qteCommande;


    public Integer getQteCommande() {
        return qteCommande;
    }

    public Date getHeureDebutChargement(){ return heureDebutChargement;}

    public Date getHeureFinChargement(){ return heureFinChargement;}

    public Integer getQteDechargee (){ return qteDechargee;}

    public Integer getMonchonDebut(){ return monchonDebut;}

    public Integer getMonchonFin() { return monchonFin;}

    public Integer getRetourneeEtrangere() { return retourneeEtrangere;}

    public Integer getRetourneeSansChap(){ return retourneeSansChap;}

    public Integer getRetourneeAutre(){return retourneeAutre;}

    public Integer getAvecEchange(){ return avecEchange;}

    public Integer getSansEchange() {return sansEchange;}

    public Integer getPriseStockPlein() { return priseStockPlein;}

    public Integer getEmpliesSurChaine(){ return empliesSurChaine;}

    public Integer getQteBD() { return qteBD;}

    public Integer getQteTM(){ return qteTM;}




	@Enumerated(EnumType.STRING)
	public StatutLigneBonLivraison getStatut() {
		return statut;
	}

    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "produit_id", foreignKey = @ForeignKey(name = "FK_LIGNE_BL_PRODUIT_ID"))  
    public Produit getProduit() {
		return produit;
	}

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bonlivraison_id", foreignKey = @ForeignKey(name = "FK_LIGNE_BL_BL_ID"))
	public BonLivraison getBonLivraison() {
		return bonLivraison;
	}

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval= true)
    @JoinColumn(name = "lignepr_id")
    public List<LignePrestation> getLignePrestation() { return lignePrestation; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motif_id", foreignKey = @ForeignKey(name = "FK_LIGNE_BL_BL_ID"))
    public Motif getMotifManque() {
        return motifManque;
    }

}
