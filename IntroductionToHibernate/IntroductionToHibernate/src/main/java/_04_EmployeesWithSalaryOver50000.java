import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee>employees = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary > 50000", Employee.class)
                        .getResultList();

        for (Employee e : employees) {
            System.out.println(e.getFirstName());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
