package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Class used to store all filters sent from front
 * used for list views
 */ 
@Setter
@Getter
public class DataFilterBean implements Serializable{
	private static final long serialVersionUID = 9053161958879009288L;
	
	public String column;    
    public Object value;
    
    public DataFilterBean(){}

	public DateFilterValue getDateValue() {
		
		return (DateFilterValue) value;
	}

	public String getStringValue() {
		return (String) value;
	}
	
	//TODO remove
	public String getValue() {
		return getStringValue();
	}
	
	public Long getLongValue() {
		String value = getStringValue();
		return Long.valueOf(value);
	}
	
	public Boolean getBooleanValue() {
		String value = getStringValue();
		return "True".equalsIgnoreCase(value);
	}
}