package com.nexse.swat.curator.integration.bean;

import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class Twitter4JBean {
    public List<Status> getTweet() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("piGhOMjSY0jusQkGQwK4rg")
                .setOAuthConsumerSecret("K9i2XhHqGFZCAhuH9o2bRPSWduvKWkjfSEehRH4")
                .setOAuthAccessToken("377414520-QR3HfosRA5ZJNrmT4elD6J9eAPNQGm154JAh84ks")
                .setOAuthAccessTokenSecret("LXCQyLd9pBWf6pTbuHisz9mZLJ4qWjlNfNKtJYHkkkE");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Paging paging = new Paging(1,100);
        List<Status> statuses = twitter.getUserTimeline(paging);

        return statuses;
    }

}
