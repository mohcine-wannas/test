package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Class used to store all filters sent from front
 * used for list views
 */ 
@Setter
@Getter
public class DataFilterBean extends DataAbstractFilterBean implements Serializable{
	private static final long serialVersionUID = 9053161958879009288L;
	
	public String column;    
    public Object value;
    
    public DataFilterBean(){}

	public DateFilterValue getDateValue() throws ParseException {
		DateFilterValue dfv = new DateFilterValue();
		@SuppressWarnings("rawtypes")
		LinkedHashMap map = (LinkedHashMap) value;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//		dfv.date1 = dateFormat.parse(map.get("date1")!=null ? (String) map.get("date1") : null);
		dfv.date1 = StringUtils.isBlank((String) map.get("date1"))  ? null : dateFormat.parse((String) map.get("date1"));
		dfv.date2 = StringUtils.isBlank((String) map.get("date2"))  ? null : dateFormat.parse((String) map.get("date2"));
		return dfv;
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