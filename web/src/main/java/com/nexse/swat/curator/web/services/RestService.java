package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;


public class RestService {
    @GET
    @Path("/channeldata/")
    public AjaxResponse<List<ChannelData>> getChannelData() {
        System.out.println("----invoking getChannelData");
        List<ChannelData> channelDataList = ChannelData.findAllChannelData();
        return new AjaxResponse<List<ChannelData>>("true",channelDataList);
    }

    @GET
    @Path("/enricheddata/{id}/")
    public AjaxResponse<EnrichedData> getEnrichedData(@PathParam("id") String channelDataId) {
        System.out.println("----invoking getEnrichedData, id is: " + channelDataId);
        EnrichedData enrichedData = EnrichedData.findEnrichedDataByChannelDataId(Long.parseLong(channelDataId));
        return new AjaxResponse<EnrichedData>("true",enrichedData);
    }


}
