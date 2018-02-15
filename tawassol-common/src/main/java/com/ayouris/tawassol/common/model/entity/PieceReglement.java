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
@Table(name = "pieceReglement", schema = "tawassol")
public class PieceReglement extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3864949940098942641L;
	@NotNull
	private String libelle;


	public String getLibelle() {
		return libelle;
	}
}
