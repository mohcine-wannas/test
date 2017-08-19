package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ClientBean;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QClient;
import com.ayouris.tawassol.repository.ClientRepository;
import com.ayouris.tawassol.service.ClientService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ConcessionnaireService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ColumnDefService columnDefService;
    
    @Autowired
    private ConcessionnaireService concessionnaireService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(ClientBean clientBean) {
        validateRequiredValues(clientBean);

        Client entity = mapper.map(clientBean, Client.class);
        
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CLIENT_CODE_ALREADY_EXISTS);
        }
    }

    private void validateRequiredValues(ClientBean clientBean){
        if(StringUtils.isBlank(clientBean.getLibelle())
                || StringUtils.isBlank(clientBean.getAbreviation())
                || StringUtils.isBlank(clientBean.getCode())){
            throw new ServiceException(ErrorMessageType.CLIENT_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<ClientBean> list(PageDataBean paginateData){
        return clientRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public ClientBean getDetails(Long id) {
        Client client = findOne(id);
        return mapper.map(client, ClientBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CLIENT);
    }

    @Override
    public List<ClientBean> getAll() {
        Iterable<Client> client = clientRepository.findAll(QClient.client.active.isTrue());
        return mapper.map(client, ClientBean.LIST_BEAN_TYPE);
    }

	@Override
	public List<ConcessionnaireBean> getAllConcessionnairesByClientId(Long id) {
		return concessionnaireService.findByClientId(id);
	}
}