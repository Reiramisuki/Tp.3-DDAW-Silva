package ar.org.centro8.curso.java.aplicaciones.test;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jpa.ClienteRepository;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestClienteRepository {
    public static void main(String[] args) { 
       //I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
       I_ClienteRepository cr = new ClienteRepository(emf);
       
       
       ////////////// SAVE ////////////////////   
       cr.save(new Cliente("Carla", "Perez", TipoDocumento.DNI,"103411101"));
       cr.save(new Cliente("Carolina", "Pereida", TipoDocumento.DNI,"10341201"));
       cr.save(new Cliente("Pedro", "Pereida", TipoDocumento.DNI,"20341001"));
       cr.save(new Cliente("José", "Figueroa", TipoDocumento.DNI,"30341001"));
       cr.save(new Cliente("Jonas", "Martinez", TipoDocumento.DNI,"40341001"));
              
       ////////////////   REMOVE Y BYID   //////////////////////
       int id = 36;
       System.out.println("***** Remove id="+id+" ********");
       
        Cliente cliente=cr.getById(id);
        if(cliente!=null && cliente.getId()!=null && cliente.getId()!=0)
              cr.remove(cliente);
       
       ////////////// UPDATE Y BYDOCUMENTO ////////////////////
       id=40;
       System.out.println("***** Update id="+id+" => Nombre=Jorge ********");
       cliente = cr.getById(id);
       if(cliente!=null && cliente.getId()!=null && cliente.getId()!=0){
           cliente.setNombre("Jorge");
           cr.update(cliente);
       }
       /////////////////////   ALL   ////////////////////////
       System.out.println("***** Todos los clientes ********");
       cr.getAll().forEach(System.out::println);
       
       /////////////////////   LIKEAPELLIDO   ////////////////////////
       System.out.println("***** Clientes cuyo apellido comienza por P ********");
       cr.getLikeApellido("P").forEach(System.out::println);
       
        /////////////////////   LIKENOMBRE   ////////////////////////
       System.out.println("***** Clientes cuyo nombre comienza por J ********");
       cr.getLikeNombre("J").forEach(System.out::println);
       
       /////////////////////   LIKENOMBREYAPELLIDO   ////////////////////////
       System.out.println("***** Clientes cuyo nombre comienza por P y apellido por P ********");
       cr.getLikeNombreYApellido("P", "P").forEach(System.out::println);
       
        /////////////////////   LIKEDOCUMENTO   ////////////////////////
       System.out.println("***** Cliente con documento DNI 41345604 ********");
       cr.getLikeDocumento(TipoDocumento.DNI.toString(), "41345606").forEach(System.out::println);
       
        /////////////////////   LIKEDOCUMENTONOMBREYAPELLIDO   ////////////////////////
       System.out.println("***** Cliente con documento DNI con documento 3 y cuyo nombre comience por J y su apellido por F ********");
       cr.getLikeDocumentoNombreYApellido(TipoDocumento.DNI.toString(), "3", "J", "F").forEach(System.out::println);
       emf.close();
    }
    
}
