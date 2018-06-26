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
@Table(name = "affectation_message_niveau", schema = "tawassol")
public class AffectationMessageNiveau extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	private Message message;
	private Niveau niveau;
	

	public AffectationMessageNiveau() {}


	@ManyToOne
	@JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMN_MESSAGE_ID"))
	public Message getMessage() {
		return message;
	}
	@ManyToOne
	@JoinColumn(name = "niveau_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMN_NIVEAU_ID"))
	public Niveau getNiveau() {
		return niveau;
	}
 
}
