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
@Table(name = "ligne_OL_vrac", schema = "tawassol")
public class LigneOrdreLivraisonVrac extends AuditEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Produit produit;
	private Integer qteCommande;

    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "produit_id", foreignKey = @ForeignKey(name = "FK_LVRAC_PROD_ID"))  
    public Produit getProduit() {
		return produit;
	}

	public Integer getQteCommande() {
		return qteCommande;
	}
 
}
