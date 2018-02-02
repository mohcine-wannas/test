package ma.salamgaz.gwic.common.model.bean;

import java.io.Serializable;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataCriteriaBean implements Serializable {

	private static final long serialVersionUID = -4758749206285068768L;
	
	/*pagination params*/
	private int pageNumber;	
	private int pageSize;

	/*sort params*/
	private String sortColumn;
	private String sortOrder;
	
	/*Filters params*/
	private List<DataFilterBean> filters;
	
	/**constructor*/
	public DataCriteriaBean(){}
	
	/**
	 * Check if pagination params defined
	 * @return true if pagination params are defined and are valid
	 */
	public boolean isPaginationDefined(){
		return pageNumber > 0 && pageSize > 0;
	}
	
	/**
	 * Check if sorting params defined
	 * @return true if sorting params are defined and are valid
	 */
	public boolean isSortingDefined(){
		return sortColumn != null && !sortColumn.isEmpty() && sortOrder != null && !sortOrder.isEmpty();
	}

	/**
	 * Check if sorting params defined
	 * @return true if sorting params are defined and are valid
	 */
	public boolean isFiltersDefined(){
		return filters != null && !filters.isEmpty();
	}

}


