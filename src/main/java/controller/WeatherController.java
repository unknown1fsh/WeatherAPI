package com.example.weatherapi.controller;

import com.example.weatherapi.entity.WeatherData;
import com.example.weatherapi.service.WeatherService;
import com.example.weatherapi.serviceimpl.WeatherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/weather")
public class WeatherController extends HttpServlet {

    private final WeatherService weatherService;

    public WeatherController() {
        this.weatherService = new WeatherServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        WeatherData weatherData = weatherService.getWeather(city);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{");
        out.printf("\"main\":\"%s\",", weatherData.getMain());
        out.printf("\"description\":\"%s\",", weatherData.getDescription());
        out.printf("\"temp\":%s", weatherData.getTemp());
        out.print("}");
        out.flush();
    }
}
