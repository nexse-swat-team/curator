package com.nexse.swat.curator.integration.bean;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TwitterBean {
    public List<Tweet> getTweet() {
        String consumerKey = "piGhOMjSY0jusQkGQwK4rg"; // The application's consumer key
        String consumerSecret = "K9i2XhHqGFZCAhuH9o2bRPSWduvKWkjfSEehRH4"; // The application's consumer secret
        String accessToken = "377414520-QR3HfosRA5ZJNrmT4elD6J9eAPNQGm154JAh84ks"; // The access token granted after OAuth authorization
        String accessTokenSecret = "LXCQyLd9pBWf6pTbuHisz9mZLJ4qWjlNfNKtJYHkkkE"; // The access token secret granted after OAuth authorization
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        List<Tweet> homeTimeline =
                twitter.timelineOperations().getHomeTimeline(1, 200);

        return homeTimeline;
    }

}
