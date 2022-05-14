import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _08_GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.createQuery("select e" +
                " from Employee e where e.id = :id", Employee.class).setParameter("id", id).getSingleResult();

        System.out.printf("%s %s - %s\n"
                , employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        List<Project> projects = employee.getProjects().stream().collect(Collectors.toList());
        Collections.sort(projects, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        projects.forEach(x -> System.out.println(x.getName()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
