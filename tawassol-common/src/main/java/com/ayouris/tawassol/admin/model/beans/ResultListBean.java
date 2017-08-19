package com.ayouris.tawassol.admin.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ayouris.tawassol.common.view.CommonView;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonView;

@Setter
@Getter
public class ResultListBean<T> implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 9024640446616381967L;

    @JsonView(CommonView.class)
    private List<T> items = new ArrayList<>();

    @JsonView(CommonView.class)
    private Long totalItems;

    public ResultListBean() {
    }

    public ResultListBean(List<T> items, Long totalItems) {
        this.items = items;
        this.totalItems = totalItems;
    }

}
