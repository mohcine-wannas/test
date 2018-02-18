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
@Table(name = "classe", schema = "tawassol")
public class Classe extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private String libelle;
	private AffectationNiveau affectationNiveau;

	public Classe() {
		
	}
	public Classe(AffectationNiveau affectationNiveau, int numeroDeClasse) {
			this.affectationNiveau= affectationNiveau;
			this.libelle = "Niveau" + affectationNiveau.getNiveau().getLibelle() + "groupe : " + numeroDeClasse;
			this.setActive(true);
	}

	public String getLibelle() {
		return libelle;
	}

	@ManyToOne
	@JoinColumn(name = "affectation_niveau_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CLASSE_AN_ID"))  
	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

 
}
