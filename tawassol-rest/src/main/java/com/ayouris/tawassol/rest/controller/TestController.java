package com.ayouris.tawassol.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ayouris.tawassol.common.model.dto.AdherentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Adherent;
import com.ayouris.tawassol.common.model.entity.CategorieSport;
import com.ayouris.tawassol.common.model.enumeration.ENUM_SITUATION_FAMILIALE;
import com.ayouris.tawassol.service.AdherentService;
import com.ayouris.tawassol.service.CategorieSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/test")
@Api(value = "test-api", description = "Opérations pour la gestion des adhérents")
public class TestController extends BaseController {



    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.PUT)
    public ResponseEntity<Adherent> update(@PathVariable("id") Long id) throws Exception {
        Adherent adherent = new Adherent();
        adherent.setNom("mohcine");
        return new ResponseEntity<Adherent>(adherent, HttpStatus.OK);
    }


}
