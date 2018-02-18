package com.ayouris.tawassol.common.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "affectation_niveau", schema = "tawassol")
public class AffectationNiveau extends RefEntity {

	
	private static final int DEFAULT_NOMBRE_DE_CLASSE = 2;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private AffectationCycle affecctationCycle;
	private Niveau niveau;
	private List<Classe> classes;
	private Integer nombreDeClasse;
	

	public AffectationNiveau() {}

	
	public AffectationNiveau(AffectationCycle affectationCycle, Niveau niveau) {
		this.setAffecctationCycle(affectationCycle);
		this.setNiveau(niveau);
		this.setActive(true);
		this.setNombreDeClasse(DEFAULT_NOMBRE_DE_CLASSE);
	}

	@ManyToOne
	@JoinColumn(name = "affecctation_cycle_id", nullable = true, foreignKey = @ForeignKey(name = "FK_AN_AC_ID")) 
	public AffectationCycle getAffecctationCycle() {
		return affecctationCycle;
	}

	@ManyToOne
	@JoinColumn(name = "niveau_id", nullable = true, foreignKey = @ForeignKey(name = "FK_AN_NIVEAU_ID")) 
	public Niveau getNiveau() {
		return niveau;
	}

	 @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval=true,
	    	    mappedBy = "affectationNiveau")
	public List<Classe> getClasses() {
		return classes;
	}
	
	@Transient
	public Integer getNombreDeClasse() {
		Integer nombreDeClasse = 0;
		if(classes != null) {
			nombreDeClasse = classes.size(); 
		}
		this.nombreDeClasse = nombreDeClasse;
		return this.nombreDeClasse;
	}


 
}
