package com.ayouris.tawassol.admin.model.beans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.enums.SiteType;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.view.CommonView;

@Setter
@Getter
public class SiteBean implements Serializable {

    private static final long serialVersionUID = -7419089138964718133L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<SiteBean>>() {
    }.getType();
    
	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Site>>() {
    }.getType();
    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private String code;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
	private SiteType siteType;
    @JsonView(CommonView.class)
	private String Libelle;
    @JsonView(CommonView.class)
	private String abreviation;
    @JsonView(CommonView.class)
	private String ville;
    @JsonView(CommonView.class)
	private Double tauxTransport;
	
	public void setCode(String code) {
		if(code != null) {
			this.code = code.toUpperCase();
		}
	}

    public boolean isActive() {
        return active;
    }
}
