package ws;

import entidade.Agenda;
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
import rn.AgendaRN;


@Path("agenda")
public class AgendaWS {

    AgendaRN AgendaRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AgendaWS
     */
    public AgendaWS() {
        AgendaRN = new AgendaRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agenda> getAnimais() {
        return (AgendaRN.listar());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Agenda adicionar(Agenda agenda,
        @Context HttpServletResponse response) {
            AgendaRN.inserir(agenda);

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
    public Agenda getAnimaisPorId(@PathParam("id") Long id) {
        return AgendaRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Agenda atualiza(@PathParam("id") Long id,
            Agenda agenda){
        agenda.setId(id);
        Agenda agendaAtualizada= AgendaRN.atualizar(agenda);
        return agendaAtualizada;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Agenda deletar(@PathParam("id") Long id){
        Agenda agendaDeletado = AgendaRN.deletar(id);
        return agendaDeletado;
    }
}
