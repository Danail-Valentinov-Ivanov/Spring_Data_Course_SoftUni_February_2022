import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("update Employee e set e.salary = e.salary * 1.12" +
                        " where e.department.id in (1, 2, 4, 11)")
                .executeUpdate();

        List<Employee> employees = entityManager.createQuery("select e from Employee e" +
                        " where e.department.name in ('Engineering', 'Tool Design', 'Information Services')"
                , Employee.class).getResultList();

        for (Employee empl : employees) {
            System.out.printf("%s %s ($%.2f)\n", empl.getFirstName(), empl.getLastName(), empl.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
