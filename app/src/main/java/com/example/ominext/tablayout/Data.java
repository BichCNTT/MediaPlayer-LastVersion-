package com.example.ominext.tablayout;

/**
 * Created by Ominext on 7/28/2017.
 */

public class Data {

    String url;
    String name;
    String time;
    String lyric;

    public Data() {

    }

    public Data(String url, String name, String time, String lyric) {
        this.url = url;
        this.name = name;
        this.time = time;
        this.lyric = lyric;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return name;
    }
}

