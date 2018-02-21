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
@Table(name = "affectation_eleve_classe", schema = "tawassol")
public class AffectationEleveClasse extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private Eleve eleve;
	private Classe classe;
	

	public AffectationEleveClasse() {}

	
	public AffectationEleveClasse(AffectationCycle affectationCycle, Eleve eleve) {
		this.setEleve(eleve);
		this.setActive(true);
	}


	@ManyToOne
	@JoinColumn(name = "eleve_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AEC_ELEVE_ID")) 
	public Eleve getEleve() {
		return eleve;
	}
	@ManyToOne
	@JoinColumn(name = "classe_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AEC_CLASSE_ID")) 
	public Classe getClasse() {
		return classe;
	}
 
}
