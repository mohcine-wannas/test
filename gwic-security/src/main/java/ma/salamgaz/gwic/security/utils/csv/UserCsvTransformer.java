//package ma.salamgaz.gwic.security.utils.csv;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import ma.salamgaz.gwic.common.model.bean.UserDetailsBean;
//import ma.salamgaz.gwic.common.util.csv.CsvContentKey;
//import ma.salamgaz.gwic.common.utils.csv.AbstractCsvDataBean;
//
//
//@Component
//public class UserCsvTransformer extends AbstractCsvDataBean<UserDetailsBean> {
//
//    @Override
//    public List<String> transform(UserDetailsBean element) {
//        List<String> line = new ArrayList<String>();
//        line.add(element.getLastname());
//        line.add(element.getFirstname());
//        line.add(element.getUsername());
//        line.add(element.getOrganization().getName());
//        line.add(element.getRole().getName());
//        line.add(transformeStatus(element.getStatus()));
//        return line;
//    }
//
//    @Override
//    public String getHeaderKey() {
//        return CsvContentKey.USER_HEADER.getKey();
//    }
//}
