package com.nexse.swat.curator.integration;

import org.junit.Test;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 20/06/12
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class Twitter4JVanillaTest {
    @Test
    public void getTimeline() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("piGhOMjSY0jusQkGQwK4rg")
                .setOAuthConsumerSecret("K9i2XhHqGFZCAhuH9o2bRPSWduvKWkjfSEehRH4")
                .setOAuthAccessToken("377414520-QR3HfosRA5ZJNrmT4elD6J9eAPNQGm154JAh84ks")
                .setOAuthAccessTokenSecret("LXCQyLd9pBWf6pTbuHisz9mZLJ4qWjlNfNKtJYHkkkE");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        User user = twitter.verifyCredentials();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
        for (Status status : statuses) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        }
    }
}
