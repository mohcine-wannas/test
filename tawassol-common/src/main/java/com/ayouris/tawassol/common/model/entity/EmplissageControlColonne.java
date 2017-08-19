package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

@Setter
@Entity
@Table(name = "emplissage_controle_colonne", schema = "tawassol")
public class EmplissageControlColonne extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;

	public String getAbreviation() {
		return abreviation;
	}

	public String getLibelle() {
		return libelle;
	}

	@NotNull
	private String abreviation;

	@NotNull
	private String libelle;

}
