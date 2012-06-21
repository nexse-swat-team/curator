package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import com.nexse.swat.curator.persistence.domain.Newsletter;
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
    @Path("/newsletter/")
    public AjaxResponse<List<Newsletter>> getNewsletter() {
        List<Newsletter> newsletters = Newsletter.findAllNewsletter();
        return new AjaxResponse<List<Newsletter>>("true",newsletters);
    }


    @PUT
    @Path("/newsletter/")
    public AjaxResponse<Long> sendNewsletter(Newsletter newsletter) {
        newsletter.persist();
        return new AjaxResponse<Long>("true",newsletter.getId());
    }

}
