package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Citerne;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class CiterneBean implements Serializable {

	private static final long serialVersionUID = -5029569008726313519L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE=new TypeToken<ArrayList<CiterneBean>>(){}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE=new TypeToken<ArrayList<Citerne>>(){}.getType();

	@JsonView(CommonView.class)
	private Long id;
	@JsonView(CommonView.class)
	private String code;
	@JsonView(CommonView.class)
	private Boolean active;
	@JsonView(CommonView.class)
	private TransporteurBean transporteur;
	@JsonView(CommonView.class)
	private String numFabrication;
	@JsonView(CommonView.class)
	private FabriquantBean fabriquant;
	@JsonView(CommonView.class)
	private Date dateFabrication;
	@JsonView(CommonView.class)
	private Date dateReepreuve;
	@JsonView(CommonView.class)
	private Integer volume;
	@JsonView(CommonView.class)
	private Boolean acceptePropane;
	@JsonView(CommonView.class)
	private Boolean accepteButane;
	@JsonView(CommonView.class)
	private Double capacitePropane;
	@JsonView(CommonView.class)
	private Double capaciteButane;
	@JsonView(CommonView.class)
	private String pressionService;

	@JsonView(CommonView.class)
	private String fabriquantLibelle;
	@JsonView(CommonView.class)
	private String transporteurLibelle;

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}
	
	public boolean isActive() {
		return active;
	}

}
