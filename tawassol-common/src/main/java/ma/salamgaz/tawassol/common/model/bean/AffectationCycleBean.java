package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.AffectationCycle;

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

	private CycleBean cycle;
	private SchoolBean school;
	private AnneeScolaireBean anneeSColaire;

}
