package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    public static void main(String[] args) {

        try {
            String location = "London"; // replace with your desired location
            String oneWeatherMapApiKey = "40475d150eaa6d28dd8a10aa0766c9f3"; // replace with your API key
            URL oneWeatherMapUrl = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + oneWeatherMapApiKey);



            HttpURLConnection oneWeatherMapC = (HttpURLConnection) oneWeatherMapUrl.openConnection();
            oneWeatherMapC.setRequestMethod("GET");
            int oneWeatherMapResponseCode = oneWeatherMapC.getResponseCode();
            if (oneWeatherMapResponseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader oneWeatherMapIn = new BufferedReader(new InputStreamReader(oneWeatherMapC.getInputStream()));
                String oneWeatherMapInputLine;
                StringBuffer oneWeatherMapResponse= new StringBuffer();
                while ((oneWeatherMapInputLine = oneWeatherMapIn.readLine()) != null) {
                    oneWeatherMapResponse.append(oneWeatherMapInputLine);
                }
                oneWeatherMapIn.close();

                System.out.println(oneWeatherMapResponse.toString()); // print the weather data to console

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}