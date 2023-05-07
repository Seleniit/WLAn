package App;

import Data.WeatherData;
import Data.WeatherDataDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {
        // Konfiguratsiooni laadimine Hibernate SessionFactory loomiseks
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // WeatherDataDao loomine
        WeatherDataDao weatherDataDao = new WeatherDataDao((EntityManager) sessionFactory);

        // Scanner objekti loomine sisendi saamiseks kasutajalt
        Scanner scanner = new Scanner(System.in);

        // Sisendi küsimine kasutajalt
        System.out.println("Sisesta linna nimi:");
        String cityName = scanner.nextLine();

        // Ilmateate pärimine OneWeatherMap API kaudu
        WeatherData weatherData = WeatherApiClient.getWeatherData(cityName); // siin on kala. siit peaks ilma andmed sisse lugema aga ... aju enam ei võta

        if (weatherData != null) {
            // Salvesta ilmateade andmebaasi
            weatherDataDao.saveWeatherData(weatherData);
            System.out.println("Ilmateade salvestatud andmebaasi.");

            // Prindi ilmateade
            System.out.println("\nLinna " + cityName + " ilmateade:");
            System.out.println("Temperatuur: " + weatherData.getTemperature() + "°C");
            System.out.println("Tundub nagu: " + weatherData.getFeelsLike() + "°C");
            System.out.println("Õhuniiskus: " + weatherData.getHumidity() + "%");
            System.out.println("Viimati uuendatud: " + weatherData.getDateTime());
        } else {
            System.out.println("Ilmateadet linna " + cityName + " kohta ei leitud.");
        }

        // Hibernate SessionFactory sulgemine
        sessionFactory.close();
    }

}