package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ville", schema = "tawassol")
public class Ville extends RefEntity {

	
	public String getLibelle() {
		return libelle;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private String libelle;
	private Pays pays;

	@ManyToOne
	@JoinColumn(name = "pays_id", nullable = false, foreignKey = @ForeignKey(name = "FK_VILLE_PAYS_ID"))  
	public Pays getPays() {
		return pays;
	}
 
}
