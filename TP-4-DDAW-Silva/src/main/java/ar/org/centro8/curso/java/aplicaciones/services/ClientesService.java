package ar.org.centro8.curso.java.aplicaciones.services;
import ar.org.centro8.curso.java.aplicaciones.connectors.Connector;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/clientes/v1")

public class ClientesService{
    private I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "<H2>Servicio de clientes v1 se encuentra activo.<H2>";
    }
    
    @GET
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    public String save(@QueryParam("nombre")String nombre,
                       @QueryParam("apellido")String apellido,
                       @QueryParam("edad")int edad,
                       @QueryParam("direccion")String direccion,
                       @QueryParam("email")String email,
                       @QueryParam("telefono")String telefono,
                       @QueryParam("tipoDocumento")TipoDocumento tipoDocumento,
                       @QueryParam("numeroDocumento")String numeroDocumento
                        )
    {   Cliente cliente = new Cliente(nombre, apellido, edad, direccion, email, telefono, tipoDocumento, numeroDocumento);
        cr.save(cliente);
        return cliente.getId()+"";
    }
       
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        return new Gson().toJson(cr.getAll());
    }
    
    @GET
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@QueryParam("id")int id){
        boolean result=false;
        try {
            Cliente cliente = cr.getById(id);
            if (cliente.getId()!=0){
                cr.remove(cliente);
                result=true;
            }
        } catch (Exception e) {
        }
        return result+"";
    }
    
    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@QueryParam("id")int id){
        Cliente cliente = cr.getById(id);
        return new Gson().toJson(cliente);
    }
    
    @GET
    @Path("/likeApellido")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeApellido(@QueryParam("apellido")String apellido){
        return new Gson().toJson(cr.getLikeApellido(apellido));
    }
    
    @GET
    @Path("/likeNombre")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeNombre(@QueryParam("nombre")String nombre){
        return new Gson().toJson(cr.getLikeNombre(nombre));
    }
    
     
    @GET
    @Path("/byDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeDocumento(@QueryParam("tipoDocumento")TipoDocumento tipoDocumento,
                                   @QueryParam("numeroDocumento")String numeroDocumento)
    {
        return new Gson().toJson(cr.getLikeDocumento(tipoDocumento.toString(), numeroDocumento));
    }}