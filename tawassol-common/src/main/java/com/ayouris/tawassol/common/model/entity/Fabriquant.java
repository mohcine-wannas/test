package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
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
@Table(name = "fabriquant", schema = "tawassol")
public class Fabriquant extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2277658097503465662L;
	@NotNull
	private String libelle;
	public String getLibelle() {
		return libelle;
	}
 
}
