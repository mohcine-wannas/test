package com.ayouris.tawassol.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.model.bean.DataFilterBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.ColumnDefService;

/**
 * 
 * @author m.wannas
 *
 * @param <T>
 * @param <ID>
 * @param <B>
 */
public abstract class GenericServiceImpl2<T extends BaseEntity, ID extends Serializable, B> extends GenericServiceImpl<T,ID>  {

   
    @Autowired
	private ColumnDefService columnDefService;

    @Autowired
    private GridRepositoryCustom<B> gridRepository;

    @Transactional
    public GridListBean<B> list(PageDataBean paginateData,ViewName viewName){
		Map<String, ColumnDef> columnDefMap = getColumnDef(viewName);
		
		if(!SecurityUtils.isCurrentUserMemberOfSiege()) {
			UpdateFilterWithTheGlobalFilter(paginateData,columnDefMap);
		}
		
		return gridRepository.findEntities(paginateData,columnDefMap);
    }
    
    public Map<String, ColumnDef> getColumnDef(ViewName viewName) {
    		return columnDefService.getColumnDefMapByViewName(viewName);
    }
    
	private void UpdateFilterWithTheGlobalFilter(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		for (Map.Entry<String, ColumnDef> entry : columnDefMap.entrySet()) {
				ColumnDef columnDef = entry.getValue();
				if(columnDef.getGlobalSiteFilter() != null && columnDef.getGlobalSiteFilter()) {
					DataFilterBean dataFiltreBean =  getfilterByFeild(pageDataBean.getFilters(),columnDef.getField());
					
						if(dataFiltreBean != null) {
							dataFiltreBean.setColumn(columnDef.getField());
							dataFiltreBean.setValue(getCurrentSiteLibelle());
						}else {
							dataFiltreBean = new DataFilterBean();
							dataFiltreBean.setColumn(columnDef.getField());
							dataFiltreBean.setValue(getCurrentSiteLibelle());
						}
						pageDataBean.getFilters().add(dataFiltreBean);
					}
					return;
				}
		
	}

	private DataFilterBean getfilterByFeild(List<DataFilterBean> filters, String field) {
		for (DataFilterBean dataFilterBean : filters) {
			if(dataFilterBean.getColumn().equals(field)) {
				return dataFilterBean;
			}
		}
		return null;
	}

	protected String getCurrentSiteLibelle() {
		return SecurityUtils.getCurrentUserSite().getLibelle();
	}
    
    


  
}
