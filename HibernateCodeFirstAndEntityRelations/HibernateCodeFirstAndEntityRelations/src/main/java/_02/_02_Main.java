package _02;

import _02.entity.Customer;
import _02.entity.Product;
import _02.entity.Sale;
import _02.entity.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class _02_Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Code_First");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product("water", 1.0, BigDecimal.valueOf(2.5));
        Customer customer = new Customer("Danail", "ivanov_dv", "2222");
        StoreLocation storeLocation = new StoreLocation("Gabrovo");
        Sale sale = new Sale(product, customer, storeLocation);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
