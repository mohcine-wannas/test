//package ma.salamgaz.gwic.security.utils.csv;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import ma.salamgaz.gwic.admin.model.beans.RoleBean;
//import ma.salamgaz.gwic.admin.model.enums.OrganizationType;
//import ma.salamgaz.gwic.common.util.csv.CsvContentKey;
//import ma.salamgaz.gwic.common.utils.csv.AbstractCsvDataBean;
//import ma.salamgaz.gwic.security.utils.SecurityUtils;
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
