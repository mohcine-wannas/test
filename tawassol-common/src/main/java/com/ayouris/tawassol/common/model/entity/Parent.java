package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ayouris.tawassol.admin.model.entity.User;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "parent")
@PrimaryKeyJoinColumn(name="user_id")  
public class Parent  extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
