package zzuli.zw.weather.domain;

public class Word {
    private String hitokoto;
    private String from;

    public Word(String hitokoto, String from) {
        this.hitokoto = hitokoto;
        this.from = from;
    }

    public Word() {
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return hitokoto+"  author:"+from;
    }
}
