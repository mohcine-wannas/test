package com.ayouris.tawassol.common.model.bean;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.enums.SiteType;
import com.ayouris.tawassol.common.model.entity.Citerne;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;


@Entity
@Table(name = "site", schema = "tawassol")
@Getter
@Setter
public class SiteBean extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SiteType siteType;
	private String libelle;
	private String abreviation;
	private String ville;
	private String tauxTransport;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE=new TypeToken<ArrayList<SiteBean>>(){}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE=new TypeToken<ArrayList<Site>>(){}.getType();

}
