//package com.ayouris.tawassol.security.utils.csv;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.ayouris.tawassol.admin.model.beans.RoleBean;
//import com.ayouris.tawassol.admin.model.enums.OrganizationType;
//import com.ayouris.tawassol.common.util.csv.CsvContentKey;
//import com.ayouris.tawassol.common.utils.csv.AbstractCsvDataBean;
//import com.ayouris.tawassol.security.utils.SecurityUtils;
//
//
//@Component
//public class RoleCsvTransformer extends AbstractCsvDataBean<RoleBean> {
//
//    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//    @Override
//    public List<String> transform(RoleBean role) {
//        List<String> line = new ArrayList<String>();
//        line.add(role.getName());
//        line.add(getOrganizationTypeLabel(role.getOrganizationType()));
//        line.add(String.valueOf(role.getRank()));
//        line.add(role.getDescription());
//        line.add(DATETIME_FORMAT.format(role.getUpdatedOn()));
//        line.add(transformeStatus(role.isEnabled()));
//
//        return line;
//    }
//
//    @Override
//    public String getHeaderKey() {
//        return CsvContentKey.ROLE_HEADER.getKey();
//    }
//
//    private String getOrganizationTypeLabel(OrganizationType organizationType) {
//        return messageSource.getMessageForLangTag(organizationType.getKey(), SecurityUtils.getCurrentUserLang());
//    }
//}
