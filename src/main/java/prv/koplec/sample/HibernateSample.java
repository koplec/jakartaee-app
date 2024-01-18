package prv.koplec.sample;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prv.koplec.jaxrs_todo.entities.User;

public class HibernateSample {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todosPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user = new User("sample999", "password999");
        em.persist(user);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
