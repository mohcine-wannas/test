package ma.salamgaz.tawassol.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.AnneeScolaireBean;
import ma.salamgaz.tawassol.common.model.bean.FabriquantBean;
import ma.salamgaz.tawassol.common.model.entity.AnneeScolaire;
import ma.salamgaz.tawassol.common.service.impl.BaseServiceImpl;
import ma.salamgaz.tawassol.repository.AnneeScolaireRepository;
import ma.salamgaz.tawassol.security.service.AnneeScolaireSecurityService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AnneeScolaireSecurityServiceImpl extends BaseServiceImpl<AnneeScolaire> implements AnneeScolaireSecurityService {

    @Autowired
    private AnneeScolaireRepository AnneeScolaireRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<AnneeScolaireBean> getAll() {
        List<AnneeScolaire> anneeScolaires = findAll();
        return mapper.map(anneeScolaires, FabriquantBean.LIST_BEAN_TYPE);
    }

	@Override
	public AnneeScolaire getCurrentAnneeScolaire() {
		List<AnneeScolaire> anneeScolaires = AnneeScolaireRepository.findByCurrentTrue();
		if(anneeScolaires != null && !anneeScolaires.isEmpty()) {
			return anneeScolaires.get(0);
		}
		return null;
	}
	
}
