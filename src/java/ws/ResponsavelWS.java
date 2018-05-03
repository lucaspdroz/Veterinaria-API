package ws;

import entidade.Responsavel;
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
import rn.ResponsavelRN;


@Path("responsavel")
public class ResponsavelWS {

    ResponsavelRN ResponsavelRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AgendaWS
     */
    public ResponsavelWS() {
        ResponsavelRN = new ResponsavelRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Responsavel> getResponsavel() {
        return (ResponsavelRN.listar());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Responsavel adicionar(Responsavel agenda,
        @Context HttpServletResponse response) {
            ResponsavelRN.inserir(agenda);

            response.setStatus(HttpServletResponse.SC_CREATED);
            try {
                response.flushBuffer();
            } catch (IOException ex) {
                throw new javax.ws.rs.InternalServerErrorException();
            }
            return agenda;
        }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responsavel getAnimaisPorId(@PathParam("id") Long id) {
        return ResponsavelRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Responsavel atualiza(@PathParam("id") Long id,
            Responsavel agenda){
        agenda.setId(id);
        Responsavel agendaAtualizada= ResponsavelRN.atualizar(agenda);
        return agendaAtualizada;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responsavel deletar(@PathParam("id") Long id){
        Responsavel agendaDeletado = ResponsavelRN.deletar(id);
        return agendaDeletado;
    }
}
