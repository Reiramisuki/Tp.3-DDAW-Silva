package ar.org.centro8.curso.java.aplicaciones.repositories.jpa;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ClienteRepository implements I_ClienteRepository{
    private EntityManagerFactory emf;
    public ClienteRepository(EntityManagerFactory emf) {
        this.emf = emf;}
    
    @Override
    public void save(Cliente cliente) {
      if(cliente == null) return;
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      em.persist(cliente);
      em.getTransaction().commit();
      em.close();
    }

    @Override
    public void remove(Cliente cliente) {
     if(cliente == null) return;
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      em.remove(em.merge(cliente));
      em.getTransaction().commit();
      em.close();
    }

    @Override
    public void update(Cliente cliente) {
      if(cliente == null) return;
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      em.persist(em.merge(cliente));
      em.getTransaction().commit();
      em.close();
    }

    @Override
    public List<Cliente> getAll() {
        return emf
                .createEntityManager()
                .createNamedQuery("Cliente.findAll")
                .getResultList();
    }}