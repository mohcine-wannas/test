package ma.salamgaz.gwic.common.util.csv;

import java.util.List;

public interface CsvDataBean<T> {

    public String build(List<T> elements);
}
