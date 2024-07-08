package com.example.weatherapi.serviceimpl;

import com.example.weatherapi.entity.WeatherData;
import com.example.weatherapi.repository.WeatherRepository;
import com.example.weatherapi.service.WeatherService;

import java.io.IOException;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl() {
        this.weatherRepository = new WeatherRepository();
    }

    @Override
    public WeatherData getWeather(String city) throws IOException {
        return weatherRepository.getWeather(city);
    }
}
