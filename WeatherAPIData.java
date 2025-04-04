import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPIData{
    private JFrame frame;
    private JTextField cityField;
    private JTextArea weatherInfoArea;

    public WeatherAPIData() {
        // Create the GUI window
        frame = new JFrame("Weather API Data");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input panel (TextField + Button)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel cityLabel = new JLabel("Enter City:");
        cityField = new JTextField(20);
        JButton fetchButton = new JButton("Get Weather");

        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);

        // Text area for displaying weather data
        weatherInfoArea = new JTextArea();
        weatherInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(weatherInfoArea);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button Click Event
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText().trim();
                if (!city.isEmpty()) {
                    displayWeatherData(city);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a city name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show the GUI
        frame.setVisible(true);
    }

    private void displayWeatherData(String city) {
        try {
            // Get location data
            JSONObject cityLocationData = getLocationData(city);
            if (cityLocationData == null) {
                weatherInfoArea.setText("Error: Unable to fetch location data.");
                return;
            }

            double latitude = (double) cityLocationData.get("latitude");
            double longitude = (double) cityLocationData.get("longitude");

            // Fetch weather data
            String weatherData = getWeatherData(latitude, longitude);
            weatherInfoArea.setText(weatherData);

        } catch (Exception e) {
            e.printStackTrace();
            weatherInfoArea.setText("Error: Unable to fetch weather data.");
        }
    }

    private JSONObject getLocationData(String city) {
        city = city.replaceAll(" ", "+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                city + "&count=1&language=en&format=json";

        try {
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            if (apiConnection.getResponseCode() != 200) {
                return null;
            }

            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getWeatherData(double latitude, double longitude) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";

            HttpURLConnection apiConnection = fetchApiResponse(url);

            if (apiConnection.getResponseCode() != 200) {
                return "Error: Could not connect to API.";
            }

            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeatherJson = (JSONObject) jsonObject.get("current");

            // Format weather data as text
            StringBuilder weatherData = new StringBuilder();
            weatherData.append("Current Time: ").append(currentWeatherJson.get("time")).append("\n");
            weatherData.append("Temperature: ").append(currentWeatherJson.get("temperature_2m")).append("°C\n");
            weatherData.append("Humidity: ").append(currentWeatherJson.get("relative_humidity_2m")).append("%\n");
            weatherData.append("Wind Speed: ").append(currentWeatherJson.get("wind_speed_10m")).append(" km/h\n");

            return weatherData.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error retrieving weather data.";
    }

    private String readApiResponse(HttpURLConnection apiConnection) {
        try {
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(apiConnection.getInputStream());

            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }
            scanner.close();

            return resultJson.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection fetchApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WeatherAPIData());
    }
}
