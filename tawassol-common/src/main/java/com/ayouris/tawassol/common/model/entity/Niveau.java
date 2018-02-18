package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "niveau", schema = "tawassol")
public class Niveau extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private String libelle;
	private Cycle cycle;
	
	
	public String getLibelle() {
		return libelle;
	}


	@ManyToOne
	@JoinColumn(name = "cycle_id", nullable = true, foreignKey = @ForeignKey(name = "FK_NIVEAU_CYCLE_ID")) 
	public Cycle getCycle() {
		return cycle;
	}


 
}
