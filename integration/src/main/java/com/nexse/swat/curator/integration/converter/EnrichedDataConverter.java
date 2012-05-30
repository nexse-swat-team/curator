package com.nexse.swat.curator.integration.converter;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import org.apache.camel.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.social.twitter.api.Tweet;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 27/05/12
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
@Converter
public class EnrichedDataConverter {

    @Converter
    public static EnrichedData toEnrichedData(ChannelData channelData){
        EnrichedData enrichedData = new EnrichedData();
        enrichedData.setChannelData(channelData);
        enrichedData.setAbstractTxt(channelData.getText());
        enrichedData.setAuthor(channelData.getFromUser());
        enrichedData.setImg(channelData.getProfileImageUrl());
        return enrichedData;
    }
}
