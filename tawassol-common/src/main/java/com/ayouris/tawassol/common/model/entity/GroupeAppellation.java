package com.ayouris.tawassol.common.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "groupe_appellation", schema = "tawassol")
public class GroupeAppellation extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	@Column(unique=true)
	private String libelle;
	
	private Boolean defaultChoice;
	private Cycle cycle;
	private List<Appellation> appellations;

	public String getLibelle() {
		return libelle;
	}

	public Boolean getDefaultChoice() {
		return defaultChoice;
	}

	@ManyToOne
	@JoinColumn(name = "cycle_id", nullable = true, foreignKey = @ForeignKey(name = "FK_GA_CYCLE_ID")) 
	public Cycle getCycle() {
		return cycle;
	}

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval=true,
    	    mappedBy = "groupeAppellation")
	public List<Appellation> getAppellations() {
		return appellations;
	}
	
	
 
}
