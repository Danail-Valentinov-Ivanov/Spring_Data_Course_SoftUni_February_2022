import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _02_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Town> towns = entityManager
                .createQuery("SELECT t FROM Town t", Town.class).getResultList();

        for (Town town: towns) {
            String name = town.getName();
            if (name.length() <= 5){
                String upperName = name.toUpperCase();
                town.setName(upperName);
                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
