package com.nexse.swat.curator.web.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestService {
    Map<Long, ChannelData> data = new HashMap<Long, ChannelData>();



    public RestService() {
        init();
    }


    @GET
    @Path("/channeldata/")
    public AjaxResponse<List<ChannelData>> getChannelData() {
        System.out.println("----invoking getChannelData");
        return new AjaxResponse<List<ChannelData>>("true",new ArrayList<ChannelData>(data.values()));
    }

    @GET
    @Path("/enricheddata/{id}/")
    public AjaxResponse<EnrichedData> getEnrichedData(@PathParam("id") String id) {
        System.out.println("----invoking getEnrichedData, id is: " + id);
        List<String> abstracts = new ArrayList<String>();
        abstracts.add(id+"-abstract1");
        abstracts.add(id+"-abstract2");
        abstracts.add(id + "-abstract3");
        List<String> imgs = new ArrayList<String>();
        imgs.add(id+"-img1");
        imgs.add(id+"-img2");
        imgs.add(id+"-img3");
        List<String> titles = new ArrayList<String>();
        titles.add(id+"-title1");
        titles.add(id+"-title2");
        titles.add(id+"-title3");
        List<String> categories = new ArrayList<String>();
        titles.add(id+"-category1");
        titles.add(id+"-category2");
        titles.add(id+"-category3");
        EnrichedData enrichedData = new EnrichedData(id,abstracts,"",imgs,"",titles,"",id+"-link",categories,"");
        return new AjaxResponse<EnrichedData>("true",enrichedData);
    }

    final void init() {
        for(long i= 1; i<5; i++ ){
            String channel="channel"+i;
            String title="title"+i;
            String abstractTxt="abstract"+i;
            String link="link"+i;
            String img="img"+i;
            data.put(i,new ChannelData(""+i,channel,title,abstractTxt,link,img));
        }
    }

}
