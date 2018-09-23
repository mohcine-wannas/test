package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "affectation_Message_user_parent", schema = "tawassol")
public class AffectationMessageUserParent extends RefEntity {


	private User parent;
	private AffectationMessageUser affectationMessageUser;
	private Boolean seen = false;
	private LocalDateTime seenDate;
	private Boolean hide = false;
	private Boolean favoris = false;

	public AffectationMessageUserParent() {}


	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AMUP_PARENT_ID"))
	public User getParent() {
		return parent;
	}

	@ManyToOne
	@JoinColumn(name = "amu_id", foreignKey = @ForeignKey(name = "FK_AMUP_AMU_ID"))
	public AffectationMessageUser getAffectationMessageUser() {
		return affectationMessageUser;
	}

	public Boolean getSeen() {
		return seen;
	}
	public Boolean getFavoris() {
		return favoris;
	}
	public Boolean getHide() {
		return hide;
	}

	public LocalDateTime getSeenDate() {
		return seenDate;
	}
}
