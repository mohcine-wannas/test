package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
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
@Table(name = "signataire", schema = "tawassol")
public class Signataire extends RefEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = -4805198036730641173L;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	@NotNull
	private String fonction;
	@NotNull
	private String tel;
	private String signature;
	private Concessionnaire concessionnaire;
	
	@ManyToOne
	@JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SIGNATAIRE_CONCESS_ID"))  
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public String getTel() {
		return tel;
	}

	public String getSignature() {
		return signature;
	}

    
}
