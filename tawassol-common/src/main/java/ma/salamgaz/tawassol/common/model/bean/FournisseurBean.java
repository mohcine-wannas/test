package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.Fournisseur;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class FournisseurBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6214113621216033605L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<FournisseurBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Fournisseur>>() {
	}.getType();

	private Long id;
	private String libelle;
	private String abreviation;
	private Boolean active;

	public boolean isActive() {
		return active;
	}
}
