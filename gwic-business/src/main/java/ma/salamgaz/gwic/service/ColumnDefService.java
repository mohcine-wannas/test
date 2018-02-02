package ma.salamgaz.gwic.service;

import java.util.List;
import java.util.Map;

import ma.salamgaz.gwic.admin.model.beans.ColumnDefBean;
import ma.salamgaz.gwic.admin.model.enums.ViewName;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;


public interface ColumnDefService {


	List<ColumnDefBean> getByViewName(ViewName viewnName);

	List<ColumnDefBean> getNoHiddenByViewName(ViewName viewnName);

	Map<String, ColumnDef> getColumnDefMapByViewName(ViewName viewnName);
}
