package ressources;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("modules")
public class ModuleRessource {
    public static ModuleBusiness mb = new ModuleBusiness();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response ajouterModule(Module module) {
        if(mb.addModule(module))
            return Response.status(Response.Status.CREATED).entity(mb.getAllModules()).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //question 2 et 3 et 6
    @GET
    @Produces("application/json")
    @Path("{matricule}")
    public Response getAllModulesOrByMatricule(@PathParam(value = "matricule") String matricule, @QueryParam(value = "codeUE") Integer codeUE) {
        if(matricule != null && !matricule.isEmpty()) {
            Module module = mb.getModuleByMatricule(matricule);
            if(module != null)
                return Response.ok(module).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }else if(codeUE != null){

            //Récupérer tous les modules  identifiée par son code (codeUE).
            UniteEnseignementBusiness ueb= new UniteEnseignementBusiness();
            UniteEnseignement ue=ueb.getUEByCode(codeUE);
            return Response.ok(mb.getModulesByUE(ue)).build();
        }
        return Response.ok(mb.getAllModules()).entity(mb.getAllModules()).build();
    }

    @DELETE
    @Path("{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (mb.deleteModule(matricule)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{matricule}")
    @Consumes("application/json")
    public Response updateModule(@PathParam(value = "matricule") String matricule,Module module) {
        if(mb.updateModule(matricule,module))
            return Response.status(Response.Status.OK).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
