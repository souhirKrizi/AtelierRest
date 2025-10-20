package ressources;

import entities.UniteEnseignement;
import filtres.Secured;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("UE") //c'est un path global 3al les méthodes lkol
public class UERessources {
    public static UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();

  //ajout d'une UE
    @Secured //pour sécuriser la méthode (il faut un token pour y accéder)
   @POST //pour dire qu'il s'agit d'une méthode post
   @Consumes("application/xml") //pour dire que le format d'entrée (objet) est xml
   @Produces("application/json") //pour dire que le format de sortie (la liste des UE retournée) est json
   //@Consumes(MediaType.APPLICATION_ATOM_XML)
    public Response ajouterUE(UniteEnseignement ue) {
       if(ueb.addUniteEnseignement(ue))
           return Response.status(Response.Status.CREATED).entity(ueb.getListeUE()).build(); //.entity(ueb.getListeUE()) ma3neha raja3ly liste des UE

             return Response.status(Response.Status.NOT_FOUND).build();

    }

    //Récupérer tous les UEs

/*@GET
@Produces("application/json") // RQ: on ne peut pas utiliser le format XML si le type de retour de la methode est Response
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getAllUE() {
    List<UniteEnseignement> listeUE = ueb.getListeUE();
    if(listeUE.isEmpty())
        return Response.status(Response.Status.NO_CONTENT).build();
    return Response.ok(listeUE).build();
    }*/

   @GET
    @Produces("application/json")


    public Response getUEBySemestreOrCode(@QueryParam(value = "semestre") Integer semestre,@QueryParam(value = "code") Integer code) {
       List<UniteEnseignement> listeUE;

       if (semestre == null ) {
           if(code == null) {
               // Aucun paramètre => on  récupére toute la liste
               listeUE = ueb.getListeUE();
           }else{
                // Paramètre fourni => on  récupére par code
                UniteEnseignement ue = ueb.getUEByCode(code);
                if(ue != null)
                     return Response.ok(ue).build();
                else
                     return Response.status(Response.Status.NOT_FOUND).build();
           }
       } else {

           // Paramètre fourni => on  récupére par semestre
           listeUE = ueb.getUEBySemestre(semestre);
       }

       if (listeUE == null || listeUE.isEmpty()) {
           return Response.status(Response.Status.NO_CONTENT).build();
       }

       return Response.ok(listeUE).build();



    }

    @DELETE
    @Path("{code}")
    public Response deleteUE(@PathParam("code") int code) {
        if (ueb.deleteUniteEnseignement(code)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("{code}")
    @Consumes("application/xml")
    @Produces("application/json")
    public Response updateUE(@PathParam(value = "code") int code,UniteEnseignement ue) {
        if(ueb.updateUniteEnseignement(code,ue))
            return Response.status(Response.Status.OK).entity(ueb.getListeUE()).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

/*@GET
@Produces("application/json")
    public Response getUEByCode(@QueryParam(value = "code") int code) {
       UniteEnseignement ue = ueb.getUEByCode(code);
       if(ue != null)
           return Response.ok(ue).build();

       return Response.status(Response.Status.NOT_FOUND).build();
    }*/






}
