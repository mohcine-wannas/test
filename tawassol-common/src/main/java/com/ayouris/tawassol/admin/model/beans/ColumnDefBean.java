package com.ayouris.tawassol.admin.model.beans;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.enums.ColumnType;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.enums.Unit;

@Setter
@Getter
public class ColumnDefBean implements Comparable<ColumnDefBean> {

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ColumnDefBean>>() {
    }.getType();
    
	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<ColumnDef>>() {
    }.getType();
    
	Long id;
	String headerName;
	String field;
	String nestedField;
	Integer width;
	Unit unit;
	Boolean editable;
	Boolean supressMenu;
	Boolean suppressSorting;
	Boolean hide;
	Boolean pinned;
	ColumnType type;
	ViewName viewName;
	Integer order;
	
	
	static public ColumnDef convert(ColumnDefBean bean) {
		ColumnDef entity = new ColumnDef();
		entity.setEditable(bean.getEditable());
		entity.setField(bean.getField());
		entity.setHeaderName(bean.getHeaderName());
		entity.setHide(bean.getHide());
		entity.setId(bean.getId());
		entity.setPinned(bean.getPinned());
		entity.setSuppressSorting(bean.getSuppressSorting());
		entity.setSupressMenu(bean.getSupressMenu());
		entity.setType(bean.getType());
		entity.setViewName(bean.getViewName());
		entity.setWidth(bean.getWidth());
		entity.setUnit(bean.getUnit());
		entity.setOrder(bean.getOrder());
		entity.setNestedField(bean.getNestedField());
		entity.setUnit(bean.getUnit());
		return entity;
	}
	
	static public ColumnDefBean convert(ColumnDef entity) {
		ColumnDefBean bean = new ColumnDefBean();
		bean.setEditable(entity.getEditable());
		bean.setField(entity.getField());
		bean.setHeaderName(entity.getHeaderName());
		bean.setHide(entity.getHide());
		bean.setId(entity.getId());
		bean.setPinned(entity.getPinned());
		bean.setSuppressSorting(entity.getSuppressSorting());
		bean.setSupressMenu(entity.getSupressMenu());
		bean.setType(entity.getType());
		bean.setViewName(entity.getViewName());
		bean.setWidth(entity.getWidth());
		bean.setOrder(entity.getOrder());
		bean.setNestedField(entity.getNestedField());
		bean.setUnit(entity.getUnit());
		
		return bean;
	}
	
	public static List<ColumnDefBean> convert(Iterable<ColumnDef> columnDefs) {
		List<ColumnDefBean> beans = new ArrayList<ColumnDefBean>();
		for (ColumnDef columnDef : columnDefs) {
			beans.add(convert(columnDef));
		}
		Collections.sort(beans);
		return beans;

	}

	@Override
	public int compareTo(ColumnDefBean o) {
		return this.order.compareTo(o.order);
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrdre(Integer order) {
		this.order = order;
	}
	
}
