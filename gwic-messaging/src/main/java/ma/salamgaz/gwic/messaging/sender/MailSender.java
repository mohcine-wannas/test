package ma.salamgaz.gwic.messaging.sender;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by chamakh on 25/03/2017.
 */
@Component("MailSenderFreeMarker")
public class MailSender  implements Serializable {
    private static final long serialVersionUID = -7421202097156930209L;
    private static final String encoding = "UTF-8";

    private final JavaMailSender javaMailSender;
    private final Configuration freeMarkerConfiguration;

    @Autowired
    public MailSender(JavaMailSender javaMailSender, Configuration freeMarkerConfiguration) {
        this.javaMailSender = javaMailSender;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public void sendMail(final String[] to, final String from, final String[] cc, final String[] bcc, final String subject, String templateName, Object model) throws IOException, TemplateException {
        Template template = freeMarkerConfiguration.getTemplate(templateName);
        final String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, encoding);
                message.setTo(to);
                message.setFrom(from);
                message.setCc(cc != null ? cc : new String[0]);
                message.setBcc(bcc != null ? bcc : new String[0]);
                message.setSubject(subject != null ? subject : "");
                message.setText(text, true);
            }
        });
    }

    public void sendMail(final String to, final String from, final String[] cc, final String[] bcc, final String subject, String templateName, Object model) throws IOException, TemplateException {
        sendMail(new String[] { to }, from, cc, bcc, subject, templateName, model);
    }
}
