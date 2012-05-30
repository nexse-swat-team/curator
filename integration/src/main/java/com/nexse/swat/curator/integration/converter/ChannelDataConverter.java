package com.nexse.swat.curator.integration.converter;

import com.nexse.swat.curator.persistence.domain.ChannelData;
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
public class ChannelDataConverter {

    @Converter
    public static ChannelData toChannelData(Tweet tweet){

        ChannelData channelData = new ChannelData();
        BeanUtils.copyProperties(tweet,channelData,new String[]{"id"});
        channelData.setOriginalId(tweet.getId());
        return channelData;
    }
}
