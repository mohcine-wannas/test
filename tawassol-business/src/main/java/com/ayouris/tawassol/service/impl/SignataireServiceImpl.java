package com.ayouris.tawassol.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Signataire;
import com.ayouris.tawassol.common.util.PropertiesHolders;
import com.ayouris.tawassol.repository.SignataireRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SignataireService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class SignataireServiceImpl extends GenericServiceImpl<Signataire, Long> implements SignataireService {

    @Autowired
    private SignataireRepository signataireRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;
    
    @Autowired
    private PropertiesHolders propertiesHolder;
    @Override
    public Long createOrUpdate(SignataireBean signataireBean) {
        validateRequiredValues(signataireBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Signataire entity = (Signataire) mapper.map(signataireBean, Signataire.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	ex.printStackTrace(); //TODO remove
        	throw new ServiceException(ErrorMessageType.SIGNATAIRE_CODE_ALREADY_EXISTS);
        }

    }

    private void validateRequiredValues(SignataireBean signataireBean){
       
      if( StringUtils.isBlank(signataireBean.getFonction())
                || StringUtils.isBlank(signataireBean.getNom())
                || StringUtils.isBlank(signataireBean.getPrenom())
                || StringUtils.isBlank(signataireBean.getTel())){
    	  
            throw new ServiceException(ErrorMessageType.SIGNATAIRE_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<SignataireBean> list(PageDataBean paginateData){
        return signataireRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    @Transactional
    public SignataireBean getDetails(Long id) {
        Signataire signataire = findOne(id);
        return mapper.map(signataire, SignataireBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.SIGNATAIRE);
    }


    @Override
    public List<SignataireBean> getAll() {
        List<Signataire> signataires = findAll();
        return mapper.map(signataires, SignataireBean.LIST_BEAN_TYPE);
    }

	@Override
	public void saveSignature(Long id, InputStream in) throws IOException {
		 Path folderPath = Paths.get(propertiesHolder.getUploadPath());
		 if(!folderPath.toFile().exists()) {
			 Files.createDirectories(folderPath);
		 }
	   	String filename = "signature_" + id + ".jpg";
        java.nio.file.Path path = Paths.get(folderPath.toString(),filename);
        Files.deleteIfExists(path);
        
        Files.copy(in, path);
        Signataire signataire = findOne(id);
        if(signataire != null) {
        	signataire.setSignature(path.toRealPath().toString());
        	save(signataire);
        }else {
        	throw new RuntimeException();
        }
		
	}
	@Override
	public File getSignature(Long id){
		Path folderPath = Paths.get(propertiesHolder.getUploadPath());
	   	String filename = "signature_" + id + ".jpg";
	   	Path path = Paths.get(folderPath.toString(),filename);
        return Files.exists(path) ?  path.toFile() : null;
	}

	@Override
	@Transactional
	public List<SignataireBean> findByConcessionnaireId(Long id) {
		List<Signataire> signataires = signataireRepository.findByConcessionnaireId(id);
		return mapper.map(signataires, SignataireBean.LIST_BEAN_TYPE);
	}


}
