package com.example.weatherapi.service;

import com.example.weatherapi.entity.WeatherData;

import java.io.IOException;

public interface WeatherService {
    WeatherData getWeather(String city) throws IOException;
}
