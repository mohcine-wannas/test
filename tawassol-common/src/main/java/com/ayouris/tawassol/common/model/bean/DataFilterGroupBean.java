package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Class used to store all filters sent from front
 * used for list views
 */ 
@Setter
@Getter
public class DataFilterGroupBean extends DataAbstractFilterBean implements Serializable{
	private static final long serialVersionUID = 9053161958879009288L;
	
	public List<DataAbstractFilterBean> filters;
    
    public String operator;
    
    public DataFilterGroupBean(){}
}