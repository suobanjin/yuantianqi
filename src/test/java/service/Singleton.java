package service;

import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;

public enum Singleton {
    WeatherService;
    private WeatherServiceImpl service;
    Singleton(){
        service = new WeatherServiceImpl();
    }

    public WeatherServiceImpl getService() {
        return service;
    }
}
