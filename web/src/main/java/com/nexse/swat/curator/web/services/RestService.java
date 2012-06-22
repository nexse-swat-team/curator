package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import com.nexse.swat.curator.persistence.domain.NewsletterData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.util.List;


public class RestService {
    private static Logger logger = LoggerFactory.getLogger(RestService.class);

    @GET
    @Path("/channeldata/")
    public AjaxResponse<List<ChannelData>> getChannelData() {
        List<ChannelData> channelDataList = ChannelData.findAllChannelData();
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
    public AjaxResponse<Long> sendNewsletter(NewsletterData newsletterData) {
        newsletterData.persist();
        return new AjaxResponse<Long>("true", newsletterData.getId());
    }

}
