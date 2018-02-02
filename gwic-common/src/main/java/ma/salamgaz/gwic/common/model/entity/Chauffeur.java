package ma.salamgaz.gwic.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "chauffeur", schema = "gwic")
public class Chauffeur extends RefEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	@NotNull
	private String cin;
	@NotNull
	private String permis;
	@NotNull
	private String nom;
	private String prenom;
    
    private String  ville;
    private String  tel;
    
    private String adresse;
    
	private Concessionnaire concessionnaire;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CHAUF_CONCESS_ID"))  
    public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}

    @Column(unique=true)
	public String getCin() {
		return cin;
	}

	public String getPermis() {
		return permis;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getVille() {
		return ville;
	}

	public String getTel() {
		return tel;
	}

	public String getAdresse() {
		return adresse;
	}
}
