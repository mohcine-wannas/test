package ma.salamgaz.gwic.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;
import ma.salamgaz.gwic.common.model.enums.NatureProduit;
import ma.salamgaz.gwic.common.model.enums.TypeChargement;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "produit", schema = "gwic")
public class Produit extends RefEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6011809991893028697L;

	@NotNull
	private String libelle;
	
	@NotNull
	private Double poids;
	@NotNull
	private NatureProduit natureProduit;
	@NotNull
	private TypeChargement typeChargement;
	
	private String unite;

	@Enumerated(EnumType.STRING)
    public NatureProduit getNatureProduit() {
		return natureProduit;
    }
	
	@Enumerated(EnumType.STRING)
	public TypeChargement getTypeChargement() {
		return typeChargement;
	}

	public String getLibelle() {
		return libelle;
	}

	public Double getPoids() {
		return poids;
	}

	public String getUnite() {
		return unite;
	}
}
