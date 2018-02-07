package ma.salamgaz.tawassol.common.model.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YounesM on 05/05/2017.
 */

@Setter
@Getter
public class BanqueListBean {

    private List<BanqueBean> Data = new ArrayList<>(0);

    private Long totalItems;

    public List<BanqueBean> getData() {
        return Data;
    }

    public void setData(List<BanqueBean> data) {
        Data = data;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
}
