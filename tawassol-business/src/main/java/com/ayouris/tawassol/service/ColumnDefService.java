package com.ayouris.tawassol.service;

import java.util.List;
import java.util.Map;

import com.ayouris.tawassol.admin.model.beans.ColumnDefBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.model.entity.ColumnDef;


public interface ColumnDefService {


	List<ColumnDefBean> getByViewName(ViewName viewnName);

	List<ColumnDefBean> getNoHiddenByViewName(ViewName viewnName);

	Map<String, ColumnDef> getColumnDefMapByViewName(ViewName viewnName);
}
