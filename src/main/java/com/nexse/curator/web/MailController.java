package com.nexse.curator.web;

import com.nexse.curator.model.Email;
import com.nexse.curator.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

@RequestMapping("/mail")
@Controller
public class MailController {
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public
    @ResponseBody
    AjaxResponse send(@RequestBody() Email email) {
        final Email emailToSend = email;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setBcc(emailToSend.getReceivers().toArray(new String[emailToSend.getReceivers().size()]));
                message.setFrom(emailToSend.getSender());
                message.setSubject(emailToSend.getSubject());
                message.setText(emailToSend.getBody(), true);
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException e) {
            return new AjaxResponse(e.getMessage());
        }
        return new AjaxResponse();
    }

}
