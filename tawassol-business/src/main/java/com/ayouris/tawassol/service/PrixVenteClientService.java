
package com.ayouris.tawassol.service;

import java.util.List;

import org.joda.time.Interval;

import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteClientBean;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.entity.Annee;
import com.ayouris.tawassol.common.model.entity.Mois;
import com.ayouris.tawassol.common.model.entity.PrixVenteClient;

public interface PrixVenteClientService extends GenericService<PrixVenteClient, Long> {

	List<PrixVenteClientBean> getAll();

	Long create(PrixVenteClientBean object);

	PrixVenteClientBean getDetails(Long id);

	GridListBean<PrixVenteClientBean> list(PageDataBean paginateData);

	Boolean isUnique(PrixVenteClientBean prixVenteClientBean);

	List<PrixVenteClientBean> overlap(PrixVenteClientBean prixVenteClientBean);

	List<PrixVenteClientBean> controle(PrixVenteClientBean object);

	List<Interval> findGap(Interval interval, Interval otherInterval);

	List<PrixVenteClientBean> AllPrixVenteInIntervalByProduct(Interval interval, ProduitBean produitBean);

	Boolean isUniquePeriodeFacturation(String mois, String annee);

}
