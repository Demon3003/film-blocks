package com.zhurawell.base.data.projection.post;

import java.math.BigInteger;

public class PostLightExtended {

    private BigInteger id;

    private String text;

    private String title;


    public PostLightExtended(BigInteger id, String text, String title) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public BigInteger getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getText() {
        return text;
    }

}
