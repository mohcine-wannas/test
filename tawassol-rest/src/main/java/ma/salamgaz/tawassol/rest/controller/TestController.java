package ma.salamgaz.tawassol.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.salamgaz.tawassol.common.model.dto.AdherentCriteriaDto;
import ma.salamgaz.tawassol.common.model.entity.Adherent;
import ma.salamgaz.tawassol.common.model.entity.CategorieSport;
import ma.salamgaz.tawassol.common.model.enumeration.ENUM_SITUATION_FAMILIALE;
import ma.salamgaz.tawassol.service.AdherentService;
import ma.salamgaz.tawassol.service.CategorieSportService;
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



    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) throws Exception {
        Adherent adherent = new Adherent();
        adherent.setNom("mohcine");
        return new ResponseEntity<Adherent>(adherent, HttpStatus.OK);
    }


}
