package com.nexse.swat.curator.integration.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 27/05/12
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class UrlParsingStrategy {

    private String toParse;
    private Boolean parseable = false;
    private String url;

    public UrlParsingStrategy(String toParse) {
        this.toParse = toParse;
        init();

    }

    public Boolean isParseable() {
        return parseable;
    }

    private void init(){
        int indexOfHttp = toParse.indexOf("http://");
        if(indexOfHttp!=-1){
            parseable=true;
            url = getUrl(toParse,indexOfHttp);
        }else{
            int indexOfHttps = toParse.indexOf("https://");
            if(indexOfHttps!=-1){
                parseable=true;
                url = getUrl(toParse,indexOfHttps);
            }
        }
    }


    private String getUrl(String toParse, int indexOfProtocol){
        String chunk = toParse.substring(indexOfProtocol);
        int nextBlankIndex = chunk.indexOf(" ");
        int nextCommaIndex = chunk.indexOf(",");
        int nextDotIndex = chunk.lastIndexOf(".");
        if(nextDotIndex<nextBlankIndex-1){
            nextDotIndex=-1;
        }

        if(nextCommaIndex!=-1){
        nextBlankIndex = Math.min(nextBlankIndex,nextCommaIndex);
        }

        if (nextDotIndex!=-1) {
            nextBlankIndex = Math.min(nextBlankIndex,nextDotIndex);
        }


        if(nextBlankIndex==-1){
            nextBlankIndex=chunk.length();
        }
        String url = chunk.substring(0,nextBlankIndex);

        if(url.endsWith("/")){
            url = url.substring(0,url.length()-1);
        }

        return url;
    }

    public String getUrl() {
        return url;
    }
}
