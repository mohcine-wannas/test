package com.ayouris.tawassol.common.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "cycle", schema = "tawassol")
public class Cycle extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	@Column(unique=true)
	private String libelle;
	private Integer nombreDesNiveaux;
	
	private List<Niveau> niveaux;
	
	
	
	public String getLibelle() {
		return libelle;
	}
	
	public Integer getNombreDesNiveaux() {
		return nombreDesNiveaux;
	}

	 @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval=true,
	    	    mappedBy = "cycle")
	public List<Niveau> getNiveaux() {
		return niveaux;
	}
}
