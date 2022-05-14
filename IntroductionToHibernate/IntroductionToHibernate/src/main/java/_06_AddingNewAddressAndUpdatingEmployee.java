import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String address = "Vitoshka 15";
        Address addressObj= new Address();
        addressObj.setText(address);
        entityManager.persist(addressObj);

        String lastName = scanner.nextLine();
        entityManager.createQuery("update Employee set address = :address where lastName = :lastName")
                        .setParameter("address", addressObj)
                .setParameter("lastName", lastName).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
