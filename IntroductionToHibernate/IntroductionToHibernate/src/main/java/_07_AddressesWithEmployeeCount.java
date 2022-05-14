import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _07_AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> resultList = entityManager
                .createQuery("select a from Address a order by a.employees.size desc ", Address.class)
                .getResultList();

        int count = 0;
        for (Address address : resultList) {
            System.out.printf("%s, %s - %d employees\n", address.getText(), address.getTown().getName()
                    , address.getEmployees().size());
            count++;
            if (count == 10) {
                break;
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
