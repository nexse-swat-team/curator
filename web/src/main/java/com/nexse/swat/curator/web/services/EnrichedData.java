package com.nexse.swat.curator.web.services;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 22/05/12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class EnrichedData {
    private String id;
    private List<String> abstracts;
    private String abstractTxt;
    private List<String> imgs;
    private String img;
    private List<String> titles;
    private String title;
    private String link;
    private List<String> categories;
    private String category;

    public EnrichedData(String id, List<String> abstracts, String abstractTxt, List<String> imgs, String img, List<String> titles, String title, String link, List<String> categories, String category) {
        this.id = id;
        this.abstracts = abstracts;
        this.abstractTxt = abstractTxt;
        this.imgs = imgs;
        this.img = img;
        this.titles = titles;
        this.title = title;
        this.link = link;
        this.categories = categories;
        this.category = category;
    }

    public EnrichedData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(List<String> abstracts) {
        this.abstracts = abstracts;
    }

    public String getAbstractTxt() {
        return abstractTxt;
    }

    public void setAbstractTxt(String abstractTxt) {
        this.abstractTxt = abstractTxt;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
