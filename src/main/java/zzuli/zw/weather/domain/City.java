package zzuli.zw.weather.domain;

public class City {
    private String cityZh;
    private String id;

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityZh='" + cityZh + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
