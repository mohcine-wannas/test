package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;
import ma.salamgaz.tawassol.common.model.enums.TypeCamion;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "camion", schema = "tawassol")
public class Camion extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176024974860812684L;

	@NotNull
	private String matricule;
	private Concessionnaire concessionnaire;
	@NotNull
	private TypeCamion typeCamion;
	@NotNull
	private Marque marque;

	@ManyToOne
	@JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CAMIONS_CONCESS_ID"))  
	public Concessionnaire getConcessionnaire() {
		return concessionnaire;
	}
	
	@ManyToOne
	@JoinColumn(name = "marque_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CAMIONS_MARQUE_ID"))  
	public Marque getMarque() {
		return marque;
	}
	
	@Column(unique = true)
	public String getMatricule() {
		return matricule;
	}

	@Enumerated(EnumType.STRING)
	public TypeCamion getTypeCamion() {
		return typeCamion;
	}
    
}
