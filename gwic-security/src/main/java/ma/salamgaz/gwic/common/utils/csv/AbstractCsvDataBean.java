package ma.salamgaz.gwic.common.utils.csv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import ma.salamgaz.gwic.common.util.ResourceBundleHelper;
import ma.salamgaz.gwic.common.util.csv.CsvContentKey;
import ma.salamgaz.gwic.common.util.csv.CsvDataBean;
import ma.salamgaz.gwic.security.utils.SecurityUtils;


public abstract class AbstractCsvDataBean<T> implements CsvDataBean<T> {

    private static final String BOM = "\uFEFF";
    private static final String CSV_SEPARATOR = ";";
    private static final String CSV_CARRIAGE_RETURN = "\n";

    @Autowired
    protected ResourceBundleHelper messageSource;

    @Override
    public String build(List<T> elements) {

        StringBuilder sb = new StringBuilder();
        if (buildingHeaderIsEnabled()) {
            sb.append(BOM);
            sb.append(StringUtils.arrayToDelimitedString(getTitles(), CSV_SEPARATOR));
            sb.append(CSV_CARRIAGE_RETURN);
        }

        for (T element : elements) {

            sb.append(StringUtils.collectionToDelimitedString(transform(element), CSV_SEPARATOR));
            sb.append(CSV_CARRIAGE_RETURN);
        }

        return sb.toString();
    }

    public String[] getTitles() {

        String value = messageSource.getMessageForLangTag(getHeaderKey(), SecurityUtils.getCurrentUserLang());
        return value.split(",");
    }

    public String transformeStatus(Boolean status) {

        if (status == null) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        CsvContentKey csvContentKey = status.booleanValue() ? CsvContentKey.STATUS_ENABLED
                : CsvContentKey.STATUS_DISABLED;
        return messageSource.getMessageForLangTag(csvContentKey.getKey(), SecurityUtils.getCurrentUserLang());
    }

    public abstract List<String> transform(T element);

    public abstract String getHeaderKey();

    protected Boolean buildingHeaderIsEnabled() {
        return true;
    }
}
