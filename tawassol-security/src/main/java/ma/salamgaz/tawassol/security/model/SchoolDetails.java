package ma.salamgaz.tawassol.security.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.common.model.bean.SchoolBean;

@Getter
@Setter
public class SchoolDetails implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3948900218866638234L;
	private SchoolBean school;
    private String anneeScolaire;
    private List<CycleBean> cycles;
    private String currentCycle;
    

}
