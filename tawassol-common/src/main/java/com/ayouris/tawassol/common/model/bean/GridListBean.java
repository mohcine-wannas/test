package com.ayouris.tawassol.common.model.bean;

import java.util.ArrayList;
import java.util.List;

public class GridListBean<T> {

	List<T> data = new ArrayList<>();
	Long totalItems;
	
	public List<T> getData() {
		return data;
	}

	public void setTotalItems(Long totalCount) {
		this.totalItems = totalCount;
	}

}
