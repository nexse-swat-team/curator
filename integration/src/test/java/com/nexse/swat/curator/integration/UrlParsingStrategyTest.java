package com.nexse.swat.curator.integration;

import com.nexse.swat.curator.integration.strategy.UrlParsingStrategy;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 27/05/12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class UrlParsingStrategyTest {
    @Test
    public void parseHttp(){
        String url="text text http://mokurl.com";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("http://mokurl.com",urlParsingStrategy.getUrl());

    }
    @Test
    public void parseHttps(){
        String url="text text https://mokurl.com";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("https://mokurl.com",urlParsingStrategy.getUrl());

    }
    @Test
    public void parseHttpInTheMiddle(){
        String url="text text http://mokurl.com text text text";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("http://mokurl.com",urlParsingStrategy.getUrl());

    }
    @Test
    public void parseHttpsInTheMiddle(){
        String url="text text https://mokurl.com text text text";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("https://mokurl.com",urlParsingStrategy.getUrl());

    }

    @Test
    public void parseHttpsInTheMiddleWithComma(){
        String url="text text https://mokurl.com, text text text";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("https://mokurl.com",urlParsingStrategy.getUrl());

    }

    @Test
    public void parseHttpInTheMiddleWithDot(){
        String url="text text http://mokurl.com. text text text";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("http://mokurl.com",urlParsingStrategy.getUrl());

    }

    @Test
    public void parseHttpInTheMiddleWithEndingSlash(){
        String url="text text http://mokurl.com/. text text text";
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(url);
        assertEquals("http://mokurl.com",urlParsingStrategy.getUrl());

    }

}
