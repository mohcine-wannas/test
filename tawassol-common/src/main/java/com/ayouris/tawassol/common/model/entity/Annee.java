
package com.ayouris.tawassol.common.model.entity;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

@Setter
@Entity
@Table(name = "annee", schema = "tawassol")
public class Annee extends AuditEntity {

	public String getLibelle() {
		return libelle;
	}

	private static final long serialVersionUID = 1L;

	@NotNull
	@Unique
	String libelle;

}
