package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;


@Setter
@Entity
@Table(name = "emplissage_controle_ligne", schema = "tawassol")
public class EmplissageControlLigne extends RefEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	@NotNull
	private String libelle;

	public String getLibelle() {
		return libelle;
	}
}
