import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _03_ContainsEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split("\\s+");
        String firstName = names[0];
        String lastName = names[1];

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            entityManager.createQuery("select e from Employee e " +
                            "where e.firstName = :firstName " +
                            "and e.lastName = :lastName", Employee.class).setParameter("firstName", firstName)
                    .setParameter("lastName", lastName).getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException exception) {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
