package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;


@Setter
@Entity
@Table(name = "ligne_ordre_livraison", schema = "tawassol")
public class LigneOrdreLivraison extends AuditEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Produit produit;
	private Integer qteCommande;
	private Integer qteDefectueuse;
	private OrdreLivraison ordreLivraison;

    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "produit_id", foreignKey = @ForeignKey(name = "FK_LIGNE_OL_PRODUIT_ID"))  
    public Produit getProduit() {
		return produit;
	}

	public Integer getQteCommande() {
		return qteCommande;
	}
 
	public Integer getQteDefectueuse() {
		return qteDefectueuse;
	}

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderlivraison_id", foreignKey = @ForeignKey(name = "FK_LIGNE_OL_OL_ID"))  
	public OrdreLivraison getOrdreLivraison() {
		return ordreLivraison;
	}
	
}
