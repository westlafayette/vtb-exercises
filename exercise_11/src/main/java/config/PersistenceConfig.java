package config;

import domain.Person;
import domain.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PersistenceConfig {

    public static EntityManagerFactory getEntityManagerFactory() {

        return new Configuration().addProperties(getProperties("persistence.properties"))
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    private static Properties getProperties(String fileName) {
        InputStream file = null;
        Properties properties = null;

        try {
            file = PersistenceConfig.class.getClassLoader().getResourceAsStream(fileName);
            properties = new Properties();
            properties.load(file);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return properties;
    }
}