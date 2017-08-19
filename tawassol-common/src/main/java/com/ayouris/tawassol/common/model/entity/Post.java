package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

@Setter
@Entity
@Table(name = "post", schema = "tawassol")
public class Post extends RefEntity {

	public Date getHeureDebut() {
		return heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public String getLibelle() {
		return libelle;
	}

	private static final long serialVersionUID = 1L;

	@NotNull
	private Date heureDebut;

	@NotNull
	private Date heureFin;

	@NotNull
	String libelle;

}
