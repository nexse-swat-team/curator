package com.nexse.swat.curator.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class TwitterTest {
    @Ignore
    @Test
    public void testSWATAuthTwitterTimeline() {
        String consumerKey = "piGhOMjSY0jusQkGQwK4rg"; // The application's consumer key
        String consumerSecret = "K9i2XhHqGFZCAhuH9o2bRPSWduvKWkjfSEehRH4"; // The application's consumer secret
        String accessToken = "377414520-lWOnNiGZQlJ1Ybua2ngdBRzrZrHnUKFaInAmgJYG"; // The access token granted after OAuth authorization
        String accessTokenSecret = "0x4CMfiRvraWNCWAWxVuOKNUCeB7Hgt50cJWpS5nyrg"; // The access token secret granted after OAuth authorization
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        List<Tweet> homeTimeline =
                twitter.timelineOperations().getHomeTimeline(1,200);

        assertNotNull(homeTimeline);
        assertTrue(homeTimeline.size() > 0);
        System.out.println("Total Messages "+ homeTimeline.size());

        for (Tweet tweet : homeTimeline) {
            String date = (new SimpleDateFormat("M/d/yy hh:mm")).format(tweet
                    .getCreatedAt());
            System.out.println("Tweet on "+date+" - "+tweet.getText());
        }
    }
}
