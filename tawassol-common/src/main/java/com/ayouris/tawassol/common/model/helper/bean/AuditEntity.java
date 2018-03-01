package com.ayouris.tawassol.common.model.helper.bean;

import java.io.Serializable;
import java.util.Date;

import com.ayouris.tawassol.common.model.enumeration.ENUM_TYPE_ACTION;

public class AuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 127548420436711534L;

	/** Colonne */
	private String colonne;

	/** OldValue */
	private String oldValue;

	/** OldValueFormat */
	private String oldValueFormat;

	/** NewValue */
	private String newValue;

	/** NewValueFormat */
	private String newValueFormat;

	/** UserId */
	private Long userId;

	/** Username */
	private String username;

	/** Type_action */
	private ENUM_TYPE_ACTION typeaction;

	/** ObjectId */
	private Long objectId;

	/** Date */
	private Date date;

	public String getColonne() {
		return colonne;
	}

	public void setColonne(String colonne) {
		this.colonne = colonne;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getOldValueFormat() {
		return oldValueFormat;
	}

	public void setOldValueFormat(String oldValueFormat) {
		this.oldValueFormat = oldValueFormat;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getNewValueFormat() {
		return newValueFormat;
	}

	public void setNewValueFormat(String newValueFormat) {
		this.newValueFormat = newValueFormat;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ENUM_TYPE_ACTION getTypeaction() {
		return typeaction;
	}

	public void setTypeaction(ENUM_TYPE_ACTION typeaction) {
		this.typeaction = typeaction;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}