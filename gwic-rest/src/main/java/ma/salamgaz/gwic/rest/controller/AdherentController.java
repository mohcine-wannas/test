package ma.salamgaz.gwic.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.salamgaz.gwic.common.model.dto.AdherentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Adherent;
import ma.salamgaz.gwic.common.model.entity.CategorieSport;
import ma.salamgaz.gwic.common.model.enumeration.ENUM_SITUATION_FAMILIALE;
import ma.salamgaz.gwic.service.AdherentService;
import ma.salamgaz.gwic.service.CategorieSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chamakh on 02/03/2017.
 */


@RequestMapping(value = "/adherent")
@Api(value = "adherent-controller", description = "Opérations pour la gestion des adhérents")
public class AdherentController extends BaseController {
    private final AdherentService adherentService;
    private final CategorieSportService categorieSportService;

    @Autowired
    public AdherentController(AdherentService adherentService, CategorieSportService categorieSportService) {
        this.adherentService = adherentService;
        this.categorieSportService = categorieSportService;
    }

    @ApiOperation(value = "Ajout d'un nouvel adhérent")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> addAdherent(@RequestBody Adherent adherent) throws Exception {
        adherent = adherentService.saveAdherent(adherent);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getSituationFamilialeList", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ENUM_SITUATION_FAMILIALE>> getSituationFamilialeList() throws Exception {
        List<ENUM_SITUATION_FAMILIALE> situationFamilialeList = Arrays.asList(ENUM_SITUATION_FAMILIALE.values());

        return new ResponseEntity<List<ENUM_SITUATION_FAMILIALE>>(situationFamilialeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/listByCriteria", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Adherent>> listByCriteria(@RequestBody AdherentCriteriaDto adherentCriteriaDto) throws Exception {
        List<Adherent> adherentList = adherentService.findAdherentListByCriteria(adherentCriteriaDto);

        return new ResponseEntity<List<Adherent>>(adherentList, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteAdherent(@RequestBody List<Adherent> adherentList) throws Exception {

        adherentService.delete(adherentList);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) throws Exception {
        Adherent adherent = adherentService.findOne(id);

        return new ResponseEntity<Adherent>(adherent, HttpStatus.OK);
    }

    @RequestMapping(value = "{id:\\d+}/categorieSportList", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<CategorieSport>> getCategorieSportByAdherent(@PathVariable("id") Long adherentId) throws Exception {
        List<CategorieSport> categorieSportList = categorieSportService.findByAdherentId(adherentId);

        return new ResponseEntity<List<CategorieSport>>(categorieSportList, HttpStatus.OK);
    }
}
