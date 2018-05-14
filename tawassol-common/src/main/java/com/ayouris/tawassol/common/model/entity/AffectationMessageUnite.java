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
@Table(name = "affectation_message_unite", schema = "tawassol")
public class AffectationMessageUnite extends RefEntity {


	/**
	 *
	 */
	private static final long serialVersionUID = 7795867047149834497L;

	private Message message;
	private Unite unite;


	public AffectationMessageUnite() {}


	@ManyToOne
	@JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMUN_MESSAGE_ID"))
	public Message getMessage() {
		return message;
	}
	@ManyToOne
	@JoinColumn(name = "unite_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMUN_UNITE_ID"))
	public Unite getUnite() {
		return unite;
	}
 
}
