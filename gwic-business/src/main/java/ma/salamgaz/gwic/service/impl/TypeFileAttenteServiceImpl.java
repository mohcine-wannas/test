package ma.salamgaz.gwic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ma.salamgaz.gwic.admin.model.enums.ViewName;
import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.TypeFileAttente;
import ma.salamgaz.gwic.repository.TypeFileAttenteRepository;
import ma.salamgaz.gwic.service.ColumnDefService;
import ma.salamgaz.gwic.service.ServiceException;
import ma.salamgaz.gwic.service.TypeFileAttenteService;

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
        List<TypeFileAttente> typeFileAttentes = findAll();
        return mapper.map(typeFileAttentes, TypeFileAttenteBean.LIST_BEAN_TYPE);
    }


}
