package com.ayouris.tawassol.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ayouris.tawassol.common.model.entity.Eleve;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 * @param <T>
 * @param <ID>
 * @param <B>
 */
public abstract class GenericServiceImpl2<T extends BaseEntity, ID extends Serializable, B> extends GenericServiceImpl<T,ID>  {

   
   
//    @Autowired
//    private ExportService<B> exportService;

//    @Transactional
//    public OutputStream export(PageDataBean paginateData,ViewName viewName){
//		Map<String, ColumnDef> columnDefMap = getColumnDef(viewName);
//		
//		if(!SecurityUtils.isCurrentUserMemberOfSiege()) {
//			UpdateFilterWithTheGlobalFilter(paginateData,columnDefMap);
//		}
//		
//		return exportService.export(gridRepository.findEntities(paginateData,columnDefMap),columnDefMap,viewName.getKey());
//    }
    


//	private DataFilterBean getfilterByFeild(List<DataAbstractFilterBean> filters, String field) {
//		for (DataAbstractFilterBean dataAbstractFilterBean : filters) {
//			if(dataAbstractFilterBean instanceof DataFilterBean) {
//				DataFilterBean dataFilterBean = (DataFilterBean) dataAbstractFilterBean;
//				if(dataFilterBean.getColumn().equals(field)) {
//					return dataFilterBean;
//				}
//			}
//		}
//		return null;
//	}

//	protected String getCurrentSiteLibelle() {
//		return SecurityUtils.getCurrentUserSite().getLibelle();
//	}
	
    @Override
    @Transactional
	public void delete(ID id){
		try {
			super.delete(id);
			flush();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.DELETE_ERROR);
		}
	}


}
