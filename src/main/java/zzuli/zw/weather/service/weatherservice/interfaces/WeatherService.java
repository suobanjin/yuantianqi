package zzuli.zw.weather.service.weatherservice.interfaces;

import zzuli.zw.weather.domain.Weather;

import java.io.IOException;

public interface WeatherService {
    Weather weatherByCityName(String cityName) throws IOException;
    Weather WeatherDefault() throws IOException;
    Weather weatherByCityId(String cityId) throws IOException;
}
