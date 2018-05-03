package ws;

import entidade.Animal;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.AnimalRN;


@Path("animal")
public class AnimalWS {

    AnimalRN AnimalRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalWS
     */
    public AnimalWS() {
        AnimalRN = new AnimalRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAnimais() {
        return (AnimalRN.listar());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Animal adicionar(Animal animal,
        @Context HttpServletResponse response) {
            AnimalRN.inserir(animal);

            response.setStatus(HttpServletResponse.SC_CREATED);
            try {
                response.flushBuffer();
            } catch (IOException ex) {
                throw new javax.ws.rs.InternalServerErrorException();
            }
            return animal;
        }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal getAnimaisPorId(@PathParam("id") Long id) {
        return AnimalRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Animal atualiza(@PathParam("id") Long id,
            Animal animal){
        animal.setId(id);
        Animal animalAtualizado = AnimalRN.atualizar(animal);
        return animalAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal deletar(@PathParam("id") Long id){
        Animal animalDeletado = AnimalRN.deletar(id);
        return animalDeletado;
    }
}
