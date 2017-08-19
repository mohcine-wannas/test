package com.ayouris.tawassol.common.model.bean;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.view.CommonView;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * 
 * @author m.wannas
 *
 * @param <T> type of bean
 */

@Setter
@Getter
public class GridListBean<T> {

	@JsonView(CommonView.class)
    private List<T> data = new ArrayList<>(0);
	@JsonView(CommonView.class)
    private Long totalItems;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
}
