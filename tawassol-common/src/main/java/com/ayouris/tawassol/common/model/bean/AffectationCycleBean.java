package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.AffectationNiveau;
import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.GroupeAppellation;
import com.ayouris.tawassol.common.model.entity.School;
import com.ayouris.tawassol.common.model.enums.ClasseNominationType;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AffectationCycleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5640825597922503307L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationCycleBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationCycle>>() {
	}.getType();

	private Long id;
	private CycleBean cycle;
	private SchoolBean school;
	private AnneeScolaireBean anneeScolaire;
	private GroupeAppellationBean groupeAppellation;
	private ClasseNominationType classeNominationType;
	private List<AffectationNiveauBean> affectationNiveaux;

	
}
