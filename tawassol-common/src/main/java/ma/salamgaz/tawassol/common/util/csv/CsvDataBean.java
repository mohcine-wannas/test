package ma.salamgaz.tawassol.common.util.csv;

import java.util.List;

public interface CsvDataBean<T> {

    public String build(List<T> elements);
}
