# WeatherAPI

This repository contains a Java-based web application designed to fetch and display weather data for a specified city using the OpenWeatherMap API. The application follows a layered architecture and provides a simple RESTful API endpoint that can be tested using Postman.

## Project Structure

The project is organized into the following packages:

- **entity**: Contains the `WeatherData` class which represents the weather data model.
- **repository**: Contains the `WeatherRepository` class responsible for making API calls to OpenWeatherMap.
- **service**: Contains the `WeatherService` interface which defines the business logic.
- **serviceimpl**: Contains the `WeatherServiceImpl` class which implements the business logic defined in the `WeatherService` interface.
- **controller**: Contains the `WeatherController` class which handles HTTP requests and responses.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17
- Maven
- IntelliJ IDEA (or any other Java IDE)
- OpenWeatherMap API Key

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_GITHUB_USERNAME/WeatherAPI.git
   cd WeatherAPI
   ```

2. Import the project into your IDE.

3. Update the API key in `WeatherRepository` class:
   ```java
   private static final String API_KEY = "99eb239ca6dcf0e223fde29018b49334"; // Replace with your OpenWeatherMap API key
   ```

4. Build and run the project:
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.example.weatherapi.Main"
   ```

### Running the API

The API will be running on `http://localhost:8080/weather`. You can get weather data for a specific city by making a GET request to:
```
http://localhost:8080/weather?city=CityName
```

Replace `CityName` with the name of the city you want to get the weather data for.

## Postman Collection

A Postman Collection is provided in the repository for testing the API.

### Importing the Postman Collection

1. Open Postman.
2. Click on "Import" in the top left corner.
3. Select the `WeatherAPI.postman_collection.json` file from the repository.
4. Use the `Get Weather by City` request to test the API.

#### Pre-request Script
This script will run before the request is sent, setting the API key in the headers and setting a timestamp variable.
```javascript
// Add a dynamic header
pm.request.headers.add({
    key: 'x-api-key',
    value: '99eb239ca6dcf0e223fde29018b49334'
});

// Set a dynamic variable
pm.variables.set("requestTimestamp", new Date().toISOString());
```

#### Tests
This script will run after the request is completed, checking the response status, structure, and timing.
```javascript
// Check the status code
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Check the JSON response structure
pm.test("Response has main, description, and temp fields", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("main");
    pm.expect(jsonData).to.have.property("description");
    pm.expect(jsonData).to.have.property("temp");
});

// Check the response time
pm.test("Response time is less than 200ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(200);
});

// Check if the response is JSON
pm.test("Response is JSON", function () {
    pm.response.to.be.json;
});
```

## License

This project is licensed under the MIT License.
