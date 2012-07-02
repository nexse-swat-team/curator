package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.NewsletterData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 25/06/12
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class MailService {
    private static Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    private String subject;
    private String from;


    public void send(final NewsletterData newsletterData) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setBcc(newsletterData.getRecipients().split(","));
                message.setFrom(getFrom());
                message.setSubject(getSubject() + " " +sdf.format(newsletterData.getCreatedAt()));
                message.setText(newsletterData.getBody(), true);
            }
        };
        this.mailSender.send(preparator);


    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
