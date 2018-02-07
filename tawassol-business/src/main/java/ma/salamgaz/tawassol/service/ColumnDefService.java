package ma.salamgaz.tawassol.service;

import java.util.List;
import java.util.Map;

import ma.salamgaz.tawassol.admin.model.beans.ColumnDefBean;
import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;


public interface ColumnDefService {


	List<ColumnDefBean> getByViewName(ViewName viewnName);

	List<ColumnDefBean> getNoHiddenByViewName(ViewName viewnName);

	Map<String, ColumnDef> getColumnDefMapByViewName(ViewName viewnName);
}
