package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

@Setter
@Entity
@Table(name = "destination", schema = "tawassol")
public class DestinationOL extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2024894095679876415L;
	
	@NotNull
	private String libelle;

	public String getLibelle() {
		return libelle;
	}
}
