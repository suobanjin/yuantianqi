package zzuli.zw.weather.domain;

import java.io.Serializable;

public class Weather implements Serializable {
    private String message;   //信息
    private int status;       //状态码
    private String date;      //日期
    private String time;      //查询的日期和时间
    private CityInfo cityInfo; //城市信息
    private Data data;        //返回的天气数据

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cityInfo=" + cityInfo +
                ", data=" + data +
                '}';
    }
}
