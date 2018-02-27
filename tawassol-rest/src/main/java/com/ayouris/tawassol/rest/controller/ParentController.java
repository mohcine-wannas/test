package com.ayouris.tawassol.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/ecoles")
@Api(value = "parent-api")
public class ParentController extends BaseController {

//	@Autowired
//	private ParentService parentService;

//    @RequestMapping( method = RequestMethod.GET)
//    public ResponseEntity<Long> updateParent(@RequestBody ParentBean parent) throws Exception {
//        return new ResponseEntity<Long>(parentService.update(parent), HttpStatus.OK);
//    }


}
