package com.nexse.swat.curator.integration.converter;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import org.apache.camel.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.social.twitter.api.Tweet;
import twitter4j.Status;
import twitter4j.User;

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
    public static ChannelData toChannelData(Tweet tweet) {

        ChannelData channelData = new ChannelData();
        BeanUtils.copyProperties(tweet, channelData, new String[]{"id"});
        channelData.setOriginalId(tweet.getId());
        return channelData;
    }

    @Converter
    public static ChannelData toChannelData(Status status) {
        ChannelData channelData = new ChannelData();
        User user;
        if (status.getRetweetedStatus() != null && status.getRetweetedStatus().getUser() != null) {
            user = status.getRetweetedStatus().getUser();
        } else {
            user = status.getUser();
        }
        channelData.setFromUser(user.getName());
        channelData.setProfileImageUrl(user.getProfileImageURL().toString());
        channelData.setText(status.getText());
        channelData.setSource(status.getSource());
        channelData.setOriginalId(status.getId());
        channelData.setCreatedAt(status.getCreatedAt());
        channelData.setFromUserScreenName(user.getScreenName());
        return channelData;
    }

}
