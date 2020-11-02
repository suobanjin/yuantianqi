package zzuli.zw.weather.domain;

import java.io.Serializable;

public class CityInfo implements Serializable {
    private String city;  //查询的城市名称
    private String citykey;  //城市的代码
    private String parent;   //当前城市的上级城市
    private String updateTime;   //更新时间

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitykey() {
        return citykey;
    }

    public void setCitykey(String citykey) {
        this.citykey = citykey;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "city='" + city + '\'' +
                ", cityKey='" + citykey + '\'' +
                ", parent='" + parent + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
