package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "ligne_file_attente", schema = "tawassol")
public class LigneFileAttente extends RefEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Produit produit;
	private Integer qteCommande;
	private Integer qteDefectueuse;
	private FileAttente fileAttente;
	private LigneFileAttenteCorrection ligneFileDattenteCorrection;

    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "produit_id", foreignKey = @ForeignKey(name = "FK_LIGNE_FA_PRODUIT_ID"))  
    public Produit getProduit() {
		return produit;
	}

	public Integer getQteCommande() {
		return qteCommande;
	}
 
	public Integer getQteDefectueuse() {
		return qteDefectueuse;
	}

	//@OneToOne
	@Transient
	public LigneFileAttenteCorrection getLigneFileDattenteCorrection() {
		return ligneFileDattenteCorrection;
	}
	
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileattente_id", foreignKey = @ForeignKey(name = "FK_LIGNE_FILE_ATTENTE_ID"))  
	public FileAttente getFileAttente() {
		return fileAttente;
	}
	
//	public boolean isCorrectionDefined() {
//		return ligneFileDattenteCorrection != null;
//	}
//
//	public Integer getRealQteCommande() {
//		if(isCorrectionDefined()) {
//			return QteCommande;
//		}else {
//			return QteCommande;
//		}
//	}
}
