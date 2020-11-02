package zzuli.zw.weather.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {
    private String shidu;   //湿度
    private int pm25;       //pm2.5
    private int pm10;      //pm10
    private String quality;    //空气质量
    private String wendu;      //当前温度
    private String ganmao;     //感冒指数
    private Forecast[] forecast;   //预测信息（15天信息）
    private Yesterday yesterday;   //昨天的信息

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public Forecast[] getForecast() {
        return forecast;
    }

    public void setForecast(Forecast[] forecast) {
        this.forecast = forecast;
    }

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    @Override
    public String toString() {
        return "Data{" +
                "shiDu='" + shidu + '\'' +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", quality='" + quality + '\'' +
                ", wenDu='" + wendu + '\'' +
                ", ganMao='" + ganmao + '\'' +
                ", forecasts=" + Arrays.toString(forecast) +
                ", yesterday=" + yesterday +
                '}';
    }
}
