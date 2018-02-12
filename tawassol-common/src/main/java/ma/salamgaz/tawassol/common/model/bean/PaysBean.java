package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.Pays;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Getter
public class PaysBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2263802195030105674L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PaysBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Pays>>() {
	}.getType();

	private String name;
	private String code;
         
}
