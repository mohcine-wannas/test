package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
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
@Table(name = "affectation_cycle", schema = "tawassol")
public class AffectationCycle extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935208501526570498L;
	private Cycle cycle;
	private School school;
	private AnneeScolaire anneeScolaire;


	@ManyToOne
	@JoinColumn(name = "cycle_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_CYCLE_ID"))  
	public Cycle getCycle() {
		return cycle;
	}

	@ManyToOne
	@JoinColumn(name = "school_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_SCHOOL_ID"))  
	public School getSchool() {
		return school;
	}
	
	@ManyToOne
	@JoinColumn(name = "AnneScolaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AC_AS_ID"))  
	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}
 
}
