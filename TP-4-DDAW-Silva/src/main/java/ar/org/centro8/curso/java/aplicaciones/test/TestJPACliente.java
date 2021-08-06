package ar.org.centro8.curso.java.aplicaciones.test;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestJPACliente {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAPU");
        EntityManager em = emf.createEntityManager();    
        
        System.out.println("****************** SAVE ******************** ");
        Cliente cliente;
        cliente= new Cliente("alejandro", "shuman", TipoDocumento.DNI, "12847395");
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println(cliente);
        
        System.out.println("****************** ALL ******************** ");
        em.createNamedQuery("Cliente.findAll").getResultList().forEach(System.out::println);
        
        System.out.println("****************** POR Apellido = shuman ******************** ");
        Query query = em.createNamedQuery("Cliente.findByApellido");
              query.setParameter("Apeliido", "shuman");  //keysense
              query.getResultList().forEach(System.out::println);

        System.out.println("****************** REMOVE id = 33 ******************** ");      
        
        query = em.createNamedQuery("Cliente.findById");
        query.setParameter("id", 33);
        cliente = (Cliente)query.getSingleResult();
        if(cliente!=null){
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        }
        
        System.out.println("****************** UPDATE id = 5 ******************** ");      
        
        query = em.createNamedQuery("Cliente.findById");
        query.setParameter("id", 5);
        cliente = (Cliente)query.getSingleResult();
        if(cliente!=null){
            cliente.setEmail("UPDATE.id.5@gmail.com");
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }
}