package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import com.nexse.swat.curator.persistence.domain.NewsletterData;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import java.util.Date;
import java.util.List;


public class RestService {
    private static Logger logger = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private MailService mailService;

    @GET
    @Path("/channeldata/")
    public AjaxResponse<List<ChannelData>> getChannelData() {
        List<ChannelData> channelDataList = ChannelData.findAllChannelData(0,300);
        return new AjaxResponse<List<ChannelData>>("true",channelDataList);
    }

    @GET
    @Path("/enricheddata/{id}/")
    public AjaxResponse<EnrichedData> getEnrichedData(@PathParam("id") String channelDataId) {
        EnrichedData enrichedData = EnrichedData.findEnrichedDataByChannelDataId(Long.parseLong(channelDataId));
        return new AjaxResponse<EnrichedData>("true",enrichedData);
    }

    @GET
    @Path("/newsletterData/")
    public AjaxResponse<List<NewsletterData>> getNewsletter() {
        List<NewsletterData> newsletterDatas = NewsletterData.findAllNewsletter();
        return new AjaxResponse<List<NewsletterData>>("true", newsletterDatas);
    }


    @PUT
    @Path("/newsletter/")
    public AjaxResponse<String> sendNewsletter(final NewsletterData newsletterData) {
        try {
            newsletterData.setCreatedAt(new Date());
            this.mailService.send(newsletterData);
        } catch (MailException e) {
            logger.error(newsletterData.toString(),e);
            return new AjaxResponse("false",e.getMessage());
        }
        newsletterData.persist();
        return new AjaxResponse<String>("true", "");
    }

}
