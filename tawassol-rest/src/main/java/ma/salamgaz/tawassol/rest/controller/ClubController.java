package ma.salamgaz.tawassol.rest.controller;

import ma.salamgaz.tawassol.common.model.entity.Club;
import ma.salamgaz.tawassol.messaging.sender.MailSender;
import ma.salamgaz.tawassol.reporting.helper.BuildReport;
import ma.salamgaz.tawassol.service.ClubService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamakh on 02/03/2017.
 */

//@RestController
@RequestMapping(value = "/club")
public class ClubController extends BaseController {
    private final ClubService clubService;
    private final MailSender mailSender;

    @Autowired
    public ClubController(ClubService clubService, @Qualifier("MailSenderFreeMarker") MailSender mailSender) {
        this.clubService = clubService;
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> addClub(@RequestBody Club club) throws Exception {
        club = clubService.saveClub(club);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/printList", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<InputStreamResource> printList() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("TableDataSource", new JREmptyDataSource(100));

        buildReport.setNomReport("tableReport");
        buildReport.setParameters(params);
        buildReport.setTypeReport(BuildReport.PDF_REPORT);
        byte[] pdf = buildReport.exportToPdf();
        int contentLengthOfStream = pdf.length;
        InputStreamResource inputStreamResource = new InputStreamResource( new ByteArrayInputStream(pdf));

        return ResponseEntity
                .ok()
                .contentLength(contentLengthOfStream)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(inputStreamResource);
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> sendMail() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("client", "SALAM GAZ");
        mailSender.sendMail("user@tawassol.ma", "admin@salamgaz.ma", null, null, "Test Mail", "test-mail.ftl", params);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
