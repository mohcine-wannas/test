package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "annee_scolaire", schema = "tawassol")
public class AnneeScolaire extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	@Column(unique=true)
	private String libelle;
	
	private Boolean current;

	public String getLibelle() {
		return libelle;
	}

	public Boolean getCurrent() {
		return current;
	}

 
}
