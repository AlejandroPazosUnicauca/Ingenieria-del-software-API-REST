package co.unicauca.travelagency.presentation.rest;

import co.unicauca.travelagency.domain.service.CustomerService;
import co.unicauca.travelagency.domain.entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("customers")
public class CustomerController {

    /**
     * Se inyecta la única implementación que hay de ProductService
     */
    @Inject
    private CustomerService service;

    public CustomerController() {
    }

    /*
    Su uso desde consola mediante client url:
    curl -X GET http://localhost:8080/API-REST-TravelAgency/resources/customers/
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return service.findAll();
    }

    /*
    Su uso desde consola mediante client url:
    curl -X GET http://localhost:8080/API-REST-TravelAgency/resources/customers/1
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer findById(@PathParam("id") String id) {
        return service.findById(id);
    }

    /*
    Su uso desde consola mediante client url:
    curl -X POST \
    http://localhost:8080/API-REST-TravelAgency/resources/customers/ 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String create(Customer cust) {
        if (service.create(cust)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente creado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo crear el cliente\",\"errores\":\"Id ya existe\"}";
        }
    }
    /*
    Su uso desde consola mediante client url:
    curl -X PUT \
    http://localhost:8080/API-REST-TravelAgency/resources/customers/\
    */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String update(Customer cust) {
        if (service.update(cust)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente modificado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo modificar el cliente\",\"errores\":\"Id no existe\"}";
        }
    }
    /*
    Su uso desde consola mediante client url:
    curl -X DELETE http://localhost:8080/API-REST-TravelAgency/resources/customers/ 
     */
    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") String id) {
        if (service.delete(id)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente borrado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo borrar el cliente\",\"errores\":\"Id no existe\"}";
        }
    }
}
