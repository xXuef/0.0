package net.m56.ckkj.mobile.tourism.bean;

/**
 * Created by dragonrong on 2017/8/8.
 */

public class GvJqBean {
    private String name;
    private int imageId;

    public GvJqBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
