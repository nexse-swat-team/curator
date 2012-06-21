package com.nexse.swat.curator.integration;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 09:04
 * To change this template use File | Settings | File Templates.
 */
public class JsoupTest {
    @Test
    public void textParsingTXT() throws IOException {
        //String url = "http://java.html.it/articoli/leggi/4009/jsoup-parsing-semplice-di-html5-in-java/";
        String url = "http://google.com";

        Document doc = Jsoup.connect(url).get();


        Set<String> buffer = new HashSet<String>();
        for (Element el : doc.getAllElements()) {
            String text = el.text();
            int upperLimit = 300;
            int lowerLimit = 100;
            if (text.length() < lowerLimit) {
                continue;
            }
            if (text.length() > upperLimit) {
                text = text.substring(0, upperLimit);
            }
            if (!buffer.contains(text)) {
                System.out.println(text);
                buffer.add(text);
            }

        }
    }

    @Test
    public void textParsingSRC() throws IOException {
        //String url = "http://java.html.it/articoli/leggi/4009/jsoup-parsing-semplice-di-html5-in-java/";
        String url = "http://repubblica.it";

        Document doc = Jsoup.connect(url).get();

        Elements media = doc.select("[src]");

        for (Element src : media) {
            if (src.tagName().equals("img"))
                System.out.println("img src: "+src.attr("abs:src")+" width: "+src.attr("width")+" height "+ src.attr("height"));
        }
    }
}

