import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _05_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String departmentName = "Research and Development";
        List<Employee> employees = entityManager.createQuery("SELECT e " +
                " FROM Employee e" +
                " WHERE e.department.name = :departmentName" +
                " ORDER BY e.salary, e.id", Employee.class)
                .setParameter("departmentName", departmentName).getResultList();

        for (Employee empl : employees) {
            System.out.printf("%s %s from %s - $%.2f\n", empl.getFirstName(), empl.getLastName()
            , departmentName, empl.getSalary());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
