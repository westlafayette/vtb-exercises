package service;

import config.PersistenceConfig;
import domain.Person;
import domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ShopService {

    private EntityManagerFactory entityManagerFactory;

    public ShopService() {
        entityManagerFactory = PersistenceConfig.getEntityManagerFactory();
    }

    public void showProductsByPerson(String personName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person person = entityManager.createQuery("SELECT p FROM Person p WHERE p.name = :personName", Person.class)
                .setParameter("personName", personName)
                .getSingleResult();

        List<Product> products = person.getProducts();
        System.out.println(products);

        entityManager.close();
    }

    public void findPersonsByProductTitle(String productName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :productName", Product.class)
                .setParameter("productName", productName)
                .getSingleResult();

        List<Person> persons = product.getPersons();
        System.out.println(persons);

        entityManager.close();
    }

    public void removePerson(String personName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Person person = entityManager.createQuery("SELECT p FROM Person p WHERE p.name = :personName", Person.class)
                .setParameter("personName", personName)
                .getSingleResult();

        entityManager.remove(person);

        entityTransaction.commit();
        entityManager.close();
    }

    public void removeProduct(String productName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Product product = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :productName", Product.class)
                .setParameter("productName", productName)
                .getSingleResult();

        entityManager.remove(product);

        entityTransaction.commit();
        entityManager.close();
    }

    public void buy(String personName, String productName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Person person = entityManager.createQuery("SELECT p FROM Person p WHERE p.name = :personName", Person.class)
                .setParameter("personName", personName)
                .getSingleResult();

        Product product = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :productName", Product.class)
                .setParameter("productName", productName)
                .getSingleResult();

        person.getProducts().add(product);

        entityTransaction.commit();
        entityManager.close();
    }
}
