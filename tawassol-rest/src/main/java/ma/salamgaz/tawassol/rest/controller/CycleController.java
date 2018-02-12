package ma.salamgaz.tawassol.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.security.service.CycleSecurityService;


@RestController
@RequestMapping(value = "/cycles")
@Api(value = "cycles")
public class CycleController extends BaseController {

	@Autowired
	private CycleSecurityService cycleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CycleBean>> getAll() throws Exception {

        return new ResponseEntity<List<CycleBean>>(cycleService.getAll(), HttpStatus.OK);
    }


}
