//package com.ayouris.tawassol.security.utils.csv;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.ayouris.tawassol.admin.model.beans.OrganizationBean;
//import com.ayouris.tawassol.common.util.csv.CsvContentKey;
//import com.ayouris.tawassol.common.utils.csv.AbstractCsvDataBean;
//
//
//@Component
//public class OrganizationCsvTransformer extends AbstractCsvDataBean<OrganizationBean> {
//
//    @Override
//    public List<String> transform(OrganizationBean element) {
//        List<String> liste = new ArrayList<String>();
//        liste.add(element.getName());
//        liste.add(element.getAcronym() == null ? "" : element.getAcronym());
//        liste.add(element.getParentOrganizationName() == null ? "" : element.getParentOrganizationName());
//        liste.add(element.getContactFirstname());
//        liste.add(element.getContactLastname());
//        liste.add(element.getContactEmail());
//        liste.add(transformeStatus(element.getStatus()));
//        return liste;
//    }
//
//    @Override
//    public String getHeaderKey() {
//        return CsvContentKey.ORGANIZATION_HEADER.getKey();
//    }
//}
