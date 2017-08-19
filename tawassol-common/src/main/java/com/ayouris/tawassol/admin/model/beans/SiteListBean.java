package com.ayouris.tawassol.admin.model.beans;

import java.util.ArrayList;
import java.util.List;



import lombok.Getter;
import lombok.Setter;;


@Setter
@Getter
public class SiteListBean {

    private List<SiteBean> Data = new ArrayList<>(0);

    private Long totalItems;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

	public List<SiteBean> getData() {
		return Data;
	}

	public void setData(List<SiteBean> data) {
		Data = data;
	}

}
