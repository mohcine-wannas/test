package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ayouris.tawassol.common.model.entity.GroupeAppellation;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class GroupeAppellationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<GroupeAppellationBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<GroupeAppellation>>() {
	}.getType();

	private Long id;
	private String libelle;
	private Boolean defaultChoice;
	private CycleBean cycle;
	private List<AppellationBean> appellations;
	private String displayName;
	
	public String toString() {
		StringBuilder name= new StringBuilder("");
		for (AppellationBean appellationBean : appellations) {
			name.append(appellationBean.getLibelle()).append(", ");
		}
		
		displayName = name.toString();
		displayName = displayName.length()>=2 ? displayName.substring(0, name.length() - 2):displayName;
		return displayName;
	}
	
	public String getDisplayName() {
		if(displayName ==null) {
			displayName = toString();
		}
		return displayName; 
	}

}
