package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "affectation_message_classe", schema = "tawassol")
public class AffectationMessageClasse extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private Message message;
	private Classe classe;
	

	public AffectationMessageClasse() {}


	@ManyToOne
	@JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMC_MESSAGE_ID")) 
	public Message getMessage() {
		return message;
	}
	@ManyToOne
	@JoinColumn(name = "classe_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMC_CLASSE_ID")) 
	public Classe getClasse() {
		return classe;
	}
 
}
