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
@Table(name = "affectation_parent_eleve", schema = "tawassol")
public class AffectationParentEleve extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6693663032298074455L;
	private Parent parent;
	private Eleve eleve;
	private Boolean enabled;
	

	public AffectationParentEleve() {}

	
	public AffectationParentEleve(Eleve eleve, Parent parent) {
		this.setParent(parent);
		this.setEleve(eleve);
		this.setActive(true);
		this.setEnabled(null);
	}


	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = false, foreignKey = @ForeignKey(name = "FK_APE_PARENT_ID")) 
	public Parent getParent() {
		return parent;
	}
	@ManyToOne
	@JoinColumn(name = "eleve_id", nullable = false, foreignKey = @ForeignKey(name = "FK_APE_ELEVE_ID")) 
	public Eleve getEleve() {
		return eleve;
	}


	public Boolean getEnabled() {
		return enabled;
	}
 
}
