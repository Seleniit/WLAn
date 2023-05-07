package Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class WeatherDataDao {
    private EntityManager entityManager;

    public WeatherDataDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveWeatherData(WeatherData weatherData) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(weatherData);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
