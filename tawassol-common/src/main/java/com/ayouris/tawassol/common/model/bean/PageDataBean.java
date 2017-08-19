package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.enums.Order;

@Setter
@Getter
public class PageDataBean implements Serializable {

	private static final long serialVersionUID = 3457732671916751849L;

	private int pageNumber;

	private int pageSize;

	private String sortColumn;

	private Order sortOrder;

	private String quickFilter;

	private List<DataFilterBean> filters;

	public boolean isDefined() {
		return pageNumber > 0 && pageSize > 0;
	}

	public boolean isFirstPage() {
		return pageNumber == 1;
	}

	public List<DataFilterBean> getFilters() {
		return filters;
	}

	public void setFilters(List<DataFilterBean> filters) {
		this.filters = filters;
	}

	/**
	 * Check if sorting params defined
	 * 
	 * @return true if sorting params are defined and are valid
	 */
	public boolean isSortingDefined() {
		return StringUtils.isNotBlank(sortColumn) && sortOrder != null;
	}

	/**
	 * Check if sorting params defined
	 * 
	 * @return true if sorting params are defined and are valid
	 */
	public boolean isFiltersDefined() {
		return CollectionUtils.isNotEmpty(filters);
	}

	public boolean isQuickFilterDefined() {
		return StringUtils.isNotEmpty(quickFilter);
	}

}
