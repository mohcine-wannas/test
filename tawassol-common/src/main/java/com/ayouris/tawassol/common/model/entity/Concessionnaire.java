package com.ayouris.tawassol.common.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "concessionnaire", schema = "tawassol")
public class Concessionnaire extends RefEntity {

	/**
	 * O
	 */
	private static final long serialVersionUID = 6176024974860812684L;

	@NotNull
	private String libelle;
	@NotNull
	private String abreviation;
	
    private String adresse;
    private String mail;
    private String ville;
    private String tel;
    private String fax;
    private String interlocuteur;
    private String mobile;
    private String registreCommerce;
    private String idFiscal;
    private String patente;
    
    private List<Signataire> signataires;
	private List<Affectation> affectations;
    private ConditionCommerciale conditionCommerciale;
    
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,
    	    mappedBy = "concessionnaire")
    public List<Signataire> getSignataires() {
		return signataires;
    }
    
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,
    	    mappedBy = "concessionnaire")
    public List<Affectation> getAffectations() {
		return affectations;
    }
	
    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conditioncommerciale_id", foreignKey = @ForeignKey(name = "FK_CONCESS_CONDCOMMERCIALE_ID"))  
	public ConditionCommerciale getConditionCommerciale() {
		return conditionCommerciale;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getMail() {
		return mail;
	}

	public String getVille() {
		return ville;
	}

	public String getTel() {
		return tel;
	}

	public String getFax() {
		return fax;
	}


	public String getInterlocuteur() {
		return interlocuteur;
	}

	public String getMobile() {
		return mobile;
	}

	public String getRegistreCommerce() {
		return registreCommerce;
	}

	public String getIdFiscal() {
		return idFiscal;
	}

	public String getPatente() {
		return patente;
	}


	public void setConditionCommerciale(ConditionCommerciale conditionCommerciale) {
		this.conditionCommerciale = conditionCommerciale;
	}
}
