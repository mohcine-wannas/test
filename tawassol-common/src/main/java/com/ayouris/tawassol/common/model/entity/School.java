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

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "ecole", schema = "tawassol")
public class School extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2277658097503465662L;
	
	private String adresse;
	private String code;
	private String codeMassar;
	private String codePostal;
	private String email;
	private String nom;
	private String siteWeb;
	private String tel;
	private String tel2;
	private String logoPath;
	
	private Ville ville;
	private Pays pays;

	private List<AffectationCycle> affectationCycles;
	
	public String getAdresse() {
		return adresse;
	}
	public String getCode() {
		return code;
	}
	public String getCodeMassar() {
		return codeMassar;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public String getEmail() {
		return email;
	}
	public String getNom() {
		return nom;
	}
	public String getSiteWeb() {
		return siteWeb;
	}
	public String getTel() {
		return tel;
	}
	public String getTel2() {
		return tel2;
	}
	public String getLogoPath() {
		return logoPath;
	}
	
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval=true,
    	    mappedBy = "school")
	public List<AffectationCycle> getAffectationCycles() {
		return affectationCycles;
    }
	
	@ManyToOne
	@JoinColumn(name = "ville_id", nullable = true, foreignKey = @ForeignKey(name = "FK_SCHOOL_VILLE_ID"))  
	public Ville getVille() {
		return ville;
	}
	
	@ManyToOne
	@JoinColumn(name = "pays_id", nullable = true, foreignKey = @ForeignKey(name = "FK_SCHOOL_PAYS_ID"))  
	public Pays getPays() {
		return pays;
	}
	
}
