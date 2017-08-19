package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.TypeFileAttenteBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QTypeFileAttente;
import com.ayouris.tawassol.common.model.entity.TypeFileAttente;
import com.ayouris.tawassol.repository.TypeFileAttenteRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.TypeFileAttenteService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class TypeFileAttenteServiceImpl extends GenericServiceImpl<TypeFileAttente, Long> implements TypeFileAttenteService {

    @Autowired
    private TypeFileAttenteRepository typeFileAttenteRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(TypeFileAttenteBean typeFileAttenteBean) {
        validateRequiredValues(typeFileAttenteBean);

        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        TypeFileAttente entity = (TypeFileAttente) mapper.map(typeFileAttenteBean, TypeFileAttente.class);
        if(entity.getTypePardefaut() != null && typeFileAttenteBean.getTypePardefaut()) {
        	setOldDefaultTypeToFalse(entity);
        }
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.TYPE_FILE_ATENTE_TYPE_ALREADY_EXISTS);
        }

    }

    private void setOldDefaultTypeToFalse(TypeFileAttente entity) {
    	TypeFileAttente oldDefaultType = null;
    	if(entity.getId() != null) {
    		 oldDefaultType = typeFileAttenteRepository.findOneByCategorieAndIdNotAndTypePardefautTrue(entity.getCategorie(),entity.getId());
    	} else {
    		 oldDefaultType = typeFileAttenteRepository.findOneByCategorieAndTypePardefautTrue(entity.getCategorie());
    	}
    		 if(oldDefaultType != null ){
			oldDefaultType.setTypePardefaut(false);
			save(oldDefaultType);
		}
	}

	private void validateRequiredValues(TypeFileAttenteBean typeFileAttenteBean){
        if(StringUtils.isBlank(typeFileAttenteBean.getType())
                || typeFileAttenteBean.getCategorie() == null){
            throw new ServiceException(ErrorMessageType.TYPE_FILE_ATTENTE_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<TypeFileAttenteBean> list(PageDataBean paginateData){
        return typeFileAttenteRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public TypeFileAttenteBean getDetails(Long id) {
        TypeFileAttente typeFileAttente = findOne(id);
        return mapper.map(typeFileAttente, TypeFileAttenteBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.TYPE_FILE_ATTENTE);
    }

    @Override
    public List<TypeFileAttenteBean> getAll() {
    	Iterable<TypeFileAttente> typeFileAttentes = typeFileAttenteRepository.findAll(QTypeFileAttente.typeFileAttente.active.isTrue());
        return mapper.map(typeFileAttentes, TypeFileAttenteBean.LIST_BEAN_TYPE);
    }


}
