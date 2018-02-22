package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "prestation", schema = "tawassol")
public class Prestation extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1008491895351199781L;
	@NotNull
	private String libelle;
	@NotNull
	private String libelleLivraison;
	
	private Boolean livree;
	
	private Boolean controlee;
	public String getLibelle() {
		return libelle;
	}
	public String getLibelleLivraison() {
		return libelleLivraison;
	}
	public Boolean getLivree() {
		return livree;
	}
	public Boolean getControlee() {
		return controlee;
	}
    
}
