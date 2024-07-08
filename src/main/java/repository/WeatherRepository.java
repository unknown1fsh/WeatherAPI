package com.example.weatherapi.repository;

import com.example.weatherapi.entity.WeatherData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeatherRepository {

    private static final String API_KEY = "99eb239ca6dcf0e223fde29018b49334";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public WeatherData getWeather(String city) throws IOException {
        String url = String.format(API_URL, city, API_KEY);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP Status Code: " + statusCode);
            if (statusCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + statusCode);
            }
            String jsonResponse = EntityUtils.toString(response.getEntity());
            System.out.println("Response JSON: " + jsonResponse);

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
            JsonObject main = jsonObject.getAsJsonObject("main");

            WeatherData weatherData = new WeatherData();
            weatherData.setMain(weather.get("main").getAsString());
            weatherData.setDescription(weather.get("description").getAsString());
            weatherData.setTemp(main.get("temp").getAsDouble());

            return weatherData;
        } finally {
            response.close();
            httpClient.close();
        }
    }
}
