package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
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
@Table(name = "affectation_Message_user", schema = "tawassol")
public class AffectationMessageUser extends RefEntity {

	
	private User user;
	private Message message;
	private Boolean seen = false;

	public AffectationMessageUser() {}


	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMU_ELEVE_ID"))
	public User getUser() {
		return user;
	}

	@ManyToOne
	@JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMU_MESSAGE_ID"))
	public Message getMessage() {
		return message;
	}

	public Boolean getSeen() {
		return seen;
	}
}
