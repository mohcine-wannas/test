package ma.salamgaz.tawassol.service.impl;

import ma.salamgaz.tawassol.admin.model.beans.ColumnDefBean;
import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;
import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.BanqueBean;
import ma.salamgaz.tawassol.common.model.bean.BanqueListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Banque;
import ma.salamgaz.tawassol.repository.BanqueRepository;
import ma.salamgaz.tawassol.service.BanqueService;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by YounesM on 05/05/2017.
 */

@Service
public class BanqueServiceImpl extends GenericServiceImpl<Banque, Long> implements BanqueService {

    @Autowired
    private BanqueRepository banqueRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(BanqueBean banqueBean) {
        validateRequiredValues(banqueBean);

        Banque entity = mapper.map(banqueBean, Banque.class);
        return save(entity).getId();
    }

    private void validateRequiredValues(BanqueBean banqueBean){
        if(StringUtils.isBlank(banqueBean.getLibelle())
                || StringUtils.isBlank(banqueBean.getAbreviation())){
            throw new ServiceException(ErrorMessageType.BANQUE_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public BanqueListBean list(PageDataBean paginateData){
        return banqueRepository.findBanques(paginateData,getColumnDef());
    }

    @Override
    public BanqueBean getDetails(Long id) {
        Banque banque = findOne(id);
        return mapper.map(banque, BanqueBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
        List<ColumnDefBean> columnDefBeans = columnDefService.getByViewName(ViewName.BANQUE);

        List<ColumnDef> columnDefs = mapper.map(columnDefBeans, ColumnDefBean.LIST_ENTITY_TYPE);

        Map<String, ColumnDef> columnDefMap = columnDefs.stream().collect(
                Collectors.toMap(ColumnDef::getField, Function.identity()));

        return columnDefMap;
    }
}
