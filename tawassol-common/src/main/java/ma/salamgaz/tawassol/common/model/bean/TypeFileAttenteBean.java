package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.TypeFileAttente;
import ma.salamgaz.tawassol.common.model.enums.CategorieTypeFileAttente;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class TypeFileAttenteBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7048629517393233469L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<TypeFileAttenteBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<TypeFileAttente>>(){}
            .getType();

    private Long id;
	private String type;
	private CategorieTypeFileAttente categorie;
	private Boolean typePardefaut;
    private Boolean active;

    public boolean isActive() {
        return active;
    }
    
	public void setType(String type) {
		if(type != null) {
			this.type = type.toUpperCase();
		}
	}
}
