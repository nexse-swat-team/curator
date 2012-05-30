package com.nexse.swat.curator.integration.bean;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 27/05/12
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PersisterBean {

    public ChannelData persist(ChannelData channelData){
        try {
            channelData.persist();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return channelData;
    }

    public EnrichedData persist(EnrichedData enrichedData){
        try {
            enrichedData.persist();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return enrichedData;
    }

}
