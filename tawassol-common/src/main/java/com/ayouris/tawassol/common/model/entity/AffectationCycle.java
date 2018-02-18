package com.ayouris.tawassol.common.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import com.ayouris.tawassol.common.model.enums.ClasseNominationType;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "affectation_cycle", schema = "tawassol")
public class AffectationCycle extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935208501526570498L;
	private Cycle cycle;
	private School school;
	private AnneeScolaire anneeScolaire;
	private GroupeAppellation groupeAppellation;
	private ClasseNominationType classeNominationType;
	private List<AffectationNiveau> affectationNiveaux;

	public AffectationCycle(School school, Cycle currentCycle, AnneeScolaire anneeScolaire, GroupeAppellation groupeAppellation) {
		this.setActive(true);
		this.setSchool(school);
		this.setAnneeScolaire(anneeScolaire);
		this.setCycle(currentCycle);
		this.setGroupeAppellation(groupeAppellation);
		this.setClasseNominationType(ClasseNominationType.ALPHABETIQUE);
		List<AffectationNiveau> affectationNiveaux = new ArrayList<>();
		if(currentCycle.getNiveaux() != null) {
			for(Niveau niveau : currentCycle.getNiveaux()) {
				AffectationNiveau affectationNiveau = new AffectationNiveau(this,niveau);
				affectationNiveaux.add(affectationNiveau);
			}			
		}
		this.setAffectationNiveaux(affectationNiveaux);
	}

	public AffectationCycle() {
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
	@JoinColumn(name = "cycle_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_CYCLE_ID"))  
	public Cycle getCycle() {
		return cycle;
	}

	@ManyToOne
	@JoinColumn(name = "school_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_SCHOOL_ID"))  
	public School getSchool() {
		return school;
	}
	
	@ManyToOne
	@JoinColumn(name = "AnneScolaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_AS_ID"))  
	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	@ManyToOne
	@JoinColumn(name = "groupeAppellation_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_GA_ID"))  
	public GroupeAppellation getGroupeAppellation() {
		return groupeAppellation;
	}

	@Enumerated(EnumType.STRING)
	public ClasseNominationType getClasseNominationType() {
		return classeNominationType;
	}

	 @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval=true,
	    	    mappedBy = "affecctationCycle")
	public List<AffectationNiveau> getAffectationNiveaux() {
		return affectationNiveaux;
	}
 
}
