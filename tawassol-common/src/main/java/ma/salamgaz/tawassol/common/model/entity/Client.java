package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "client", schema = "tawassol")
public class Client extends RefEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private String libelle;
	private String abreviation;
    private String adresse;
	
    private Group groupe;
    
    private String interlocuteur;
    private String ville;
    private String tel;
    private String mobile;
    private String fax;
    private String email;
    private String registreCommerce;
    private String idFiscal;
    private String patente;
    private Boolean gestionSolde;

    @ManyToOne  
    public Group getGroupe() {
		return groupe;
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

	public String getInterlocuteur() {
		return interlocuteur;
	}

	public String getVille() {
		return ville;
	}

	public String getTel() {
		return tel;
	}

	public String getMobile() {
		return mobile;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
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

	public Boolean getGestionSolde() {
		return gestionSolde;
	}
}
