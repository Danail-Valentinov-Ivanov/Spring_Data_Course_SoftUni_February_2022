import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class _09_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> projects = entityManager
                .createQuery("select p from Project p order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList();

        Collections.sort(projects, (p1, p2) -> p1.getName().compareTo(p2.getName()));

        String pattern = "YYYY-MM-dd HH:mm:ss[.n]";
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern(pattern);

        for (Project proj : projects) {
            String startDate;
            String endDate;
            if (proj.getStartDate() == null) {
                startDate = "null";
            } else {
                startDate = formattedDate.format(proj.getStartDate());
            }

            if (proj.getEndDate() == null) {
                endDate = "null";
            } else {
                endDate = formattedDate.format(proj.getEndDate());
            }

            System.out.printf("Project name: %s\n", proj.getName());
            System.out.printf("        Project Description: %s\n", proj.getDescription());
            System.out.printf("        Project Start Date: %s\n", startDate);
            System.out.printf("        Project End Date: %s\n", endDate);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
