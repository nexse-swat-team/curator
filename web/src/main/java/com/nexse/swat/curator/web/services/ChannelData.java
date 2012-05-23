package com.nexse.swat.curator.web.services;


public class ChannelData {
    private String id;
    private String channel;
    private String title;
    private String abstractTxt;
    private String link;
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractTxt() {
        return abstractTxt;
    }

    public void setAbstractTxt(String abstractTxt) {
        this.abstractTxt = abstractTxt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ChannelData(String id, String chnannel, String title, String abstractTxt, String link, String img) {
        this.id = id;
        this.channel = chnannel;
        this.title = title;
        this.abstractTxt = abstractTxt;
        this.link = link;
        this.img = img;
    }

    public ChannelData() {
    }
}
