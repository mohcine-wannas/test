package ma.salamgaz.tawassol.rest.controller;

import ma.salamgaz.tawassol.common.model.dto.DocumentCriteriaDto;
import ma.salamgaz.tawassol.common.model.entity.Document;
import ma.salamgaz.tawassol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chamakh on 02/03/2017.
 */

@RequestMapping(value = "/document")
public class DocumentController extends BaseController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping(value = "/document/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> addDocument(@RequestBody Document document) throws Exception {
        document = documentService.saveDocument(document);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/listByCriteria", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Document>> listByCriteria(@RequestBody DocumentCriteriaDto documentCriteriaDto) throws Exception {
        List<Document> documentList = documentService.findDocumentListByCriteria(documentCriteriaDto);

        return new ResponseEntity<List<Document>>(documentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteDocument(@RequestBody List<Document> documentList) throws Exception {

        documentService.delete(documentList);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
