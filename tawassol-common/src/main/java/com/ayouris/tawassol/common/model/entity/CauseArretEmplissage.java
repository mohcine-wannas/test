package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

@Setter
@Entity
@Table(name = "causeArretEmplissage", schema = "tawassol")
public class CauseArretEmplissage extends RefEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	String libelle;

	public String getLibelle() {
		return libelle;
	}
}
