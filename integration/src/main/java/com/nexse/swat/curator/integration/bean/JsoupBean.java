package com.nexse.swat.curator.integration.bean;

import com.nexse.swat.curator.integration.strategy.UrlParsingStrategy;
import com.nexse.swat.curator.persistence.domain.EnrichedData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 27/05/12
 * Time: 09:55
 * To change this template use File | Settings | File Templates.
 */
public class JsoupBean {
    private static final Logger log =
            LoggerFactory.getLogger(JsoupBean.class);

    private Integer articleTxtMaxLength;
    private Integer articleTxtMinLength;

    public EnrichedData enrich(EnrichedData enrichedData) throws IOException {
        log.info("Enriching {}", enrichedData.getChannelData().getText());
        log.trace("Enriching {}",enrichedData);
        UrlParsingStrategy urlParsingStrategy = new UrlParsingStrategy(enrichedData.getChannelData().getText());
        String url = "";
        try {
            if (urlParsingStrategy.isParseable()) {
                log.debug("{} contains an url, it will be fetched", enrichedData.getChannelData().getText());
                url = urlParsingStrategy.getUrl();
                enrichedData.setLink(url);
                Document doc = Jsoup.connect(url).timeout(60000).get();
                Set<String> buffer = new HashSet<String>();
                StringBuilder stringBuffer = new StringBuilder("");
                for (Element el : doc.getAllElements()) {
                    String text = el.text();
                    if (text.length() < articleTxtMinLength) {
                        continue;
                    }
                    if (text.length() > articleTxtMaxLength) {
                        text = text.substring(0, articleTxtMaxLength);
                    }
                    if (!buffer.contains(text)) {
                        buffer.add(text);
                        stringBuffer.append(text);
                    }

                }
                enrichedData.setArticleTxt(stringBuffer.toString());
                enrichedData.setTitle(enrichedData.getTitle().replaceFirst(url,""));
                enrichedData.setTitle(enrichedData.getTitle().replaceFirst("  ",""));
                log.debug("text fetched from {} : {}",url,stringBuffer.toString());

            }
        } catch (Exception e) {
            log.warn("the parsing of this url: "+url+" generates the following error",e);
            enrichedData.setArticleTxt("there was an error during enrichment phase");
        }

        log.trace("data after enrichment: {}",enrichedData);
        return enrichedData;
    }

    public Integer getArticleTxtMaxLength() {
        return articleTxtMaxLength;
    }

    public void setArticleTxtMaxLength(Integer articleTxtMaxLength) {
        this.articleTxtMaxLength = articleTxtMaxLength;
    }

    public Integer getArticleTxtMinLength() {
        return articleTxtMinLength;
    }

    public void setArticleTxtMinLength(Integer articleTxtMinLength) {
        this.articleTxtMinLength = articleTxtMinLength;
    }
}
