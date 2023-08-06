package com.example.myapplicatio;

public class presertorabsent {
    String heading;
    int present;
    boolean visiblity;

    public presertorabsent(String heading, int titleImage, boolean visiblity) {
        this.heading = heading;
        this.present = titleImage;
        this.visiblity = false;
    }

    public boolean isVisiblity() {
        return visiblity;
    }

    public void setVisiblity(boolean visiblity) {
        this.visiblity = visiblity;
    }
}
