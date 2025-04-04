# REST-API-CLIENT

COMPANY: CODTECH IT SOLUTIONS

NAME: KHUSHI SAHU

INTERN ID:CT4MTGYC

DOMAIN: JAVA PROGRAMMING

DURATION: 16 WEEKS

MENTOR: NEELA SANTOSH

# DESCRIPTION:
# Objective:
Create a Java program that fetches data from a public REST API and displays it.

# Concepts Used:
•	Java HTTP Client (HttpURLConnection)
•	JSON Parsing (org.json or Gson)
•	Exception Handling (try-catch)

# Steps to Implement:
1.	Choose a public REST API (e.g., OpenWeather API).
2.	Send an HTTP GET request to fetch data.
3.	Parse the JSON response.
4.	Display the structured data in the console/GUI through Swing.

# Overview
The Weather API Client is a Java-based application that fetches real-time weather data using a public REST API. It allows users to enter a city name and retrieve structured weather details such as temperature, humidity, wind speed, and time of the report. The application features a graphical user interface (GUI) built with Swing, making it easy to use and visually appealing.

# Features 🚀
🌍 Fetch real-time weather data for any city worldwide.

🖥️ Graphical User Interface (GUI) for user-friendly interaction.

⚡ Fast and lightweight using HttpURLConnection for API calls.

📊 Displays structured weather information, including:
        Current temperature (°C)
        Humidity (%)
        Wind speed (km/h)
        Timestamp of the data retrieval

🔄 Uses Open-Meteo API for accurate weather forecasts.

🔗 Modular and well-documented code for easy extension.

# How It Works 🛠️
1. The user enters a city name in the input field.
2. The application sends a request to the Geocoding API to fetch the latitude and longitude of the city.
3. The retrieved coordinates are then used to call the Weather API, which provides the latest weather details.
4. The results are displayed in the GUI with structured formatting.

# API Used 🌐
This project utilizes Open-Meteo’s Free API for accurate and real-time weather updates.
1.Geocoding API (for fetching latitude & longitude): https://geocoding-api.open-meteo.com/v1/search?name=CityName&count=1&language=en&format=json
2.Weather API (for weather details): https://api.open-meteo.com/v1/forecast?latitude=XX.XX&longitude=YY.YY&current=temperature_2m,relative_humidity_2m,wind_speed_10m

# Technologies Used 🛠️
* Java (Core Programming)
* Swing (Graphical User Interface)
* JSON-Simple (Parsing API response)
* HttpURLConnection (Making HTTP requests)

# Future Enhancements 🏗️
🔹 Add 7-day weather forecast support.
🔹 Improve GUI design with charts & graphs.
🔹 Implement caching to reduce API calls.
🔹 Enable multi-language support.

# Output:
