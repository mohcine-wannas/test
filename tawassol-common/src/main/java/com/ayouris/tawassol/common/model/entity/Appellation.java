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
@Table(name = "appellation", schema = "tawassol")
public class Appellation extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1504028635951365073L;

	private String libelle;
	
	private GroupeAppellation groupeAppellation;
	
	private Niveau niveau;

	public String getLibelle() {
		return libelle;
	}

	@ManyToOne
	@JoinColumn(name = "group_appellation_id", nullable = false, foreignKey = @ForeignKey(name = "FK_APPELLATION_GA_ID")) 
	public GroupeAppellation getGroupeAppellation() {
		return groupeAppellation;
	}
	
	@ManyToOne
	@JoinColumn(name = "niveau_id", nullable = false, foreignKey = @ForeignKey(name = "FK_APPELLATION_NIVEAU_ID")) 
	public Niveau getNiveau() {
		return niveau;
	}

}
