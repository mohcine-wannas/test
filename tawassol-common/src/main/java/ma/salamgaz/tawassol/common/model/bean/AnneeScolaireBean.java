package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.AnneeScolaire;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AnneeScolaireBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AnneeScolaireBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AnneeScolaire>>() {
	}.getType();

	private String libelle;
	private Boolean current;

}
