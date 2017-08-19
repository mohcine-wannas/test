package com.ayouris.tawassol.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ayouris.tawassol.common.model.enumeration.ProfilType;
import com.ayouris.tawassol.common.model.enums.*;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.admin.model.enums.SiteType;

@Component("enumResource")
@Path("/enum")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnumResource  {

    @GET
    @Path("siteType")
    public Response getAllSiteType() {
    	List<SiteType> siteTypeList = Arrays.asList(SiteType.values());
        return Response.ok(siteTypeList).build();
    }
    
    @GET
    @Path("journeeActStatut")
    public Response getJAStatuts() {
    	List<StatutJourneeActivite> journeeActStatutList = Arrays.asList(StatutJourneeActivite.values());
    	return Response.ok(journeeActStatutList).build();
    }
    
    @GET
    @Path("ficheMarcheStatut")
    public Response getFicheMarcheStatuts() {
    	List<StatutFicheMarche> ficheMarcheStatutList = Arrays.asList(StatutFicheMarche.values());
        return Response.ok(ficheMarcheStatutList).build();
    }
    
    @GET
    @Path("journeeActActivite")
    public Response getJAActivitesgetJAActivites() {
    	List<ActiviteJourneeActivite> activiteJourneeActiviteList = Arrays.asList(ActiviteJourneeActivite.values());
        return Response.ok(activiteJourneeActiviteList).build();
    }
    @GET    
    @Path("fileAttenteStatut")
    public Response getFileAttenteTypes() {
    	List<StatutFileAttente> fileAttenteStatuts = Arrays.asList(StatutFileAttente.values());
        return Response.ok(fileAttenteStatuts).build();
    }
    
    @GET
    @Path("ordreLivraisonStatut")
    public Response getOrdreLivraisonStaut() {
    	List<StatutOrdreLivraison> statutOrdreLivraisons = Arrays.asList(StatutOrdreLivraison.values());
    	return Response.ok(statutOrdreLivraisons).build();
    }
    
    @GET
    @Path("statutEmplissage")
    public Response getEmplissageStaut() {
    	List<StatutEmplissage> statutsEmplissage = Arrays.asList(StatutEmplissage.values());
    	return Response.ok(statutsEmplissage).build();
    }
    
    @GET
    @Path("origineEmplissage")
    public Response getOrigineEmplissage() {
    	List<OrigineEmplissage> originesEmplissage = Arrays.asList(OrigineEmplissage.values());
        return Response.ok(originesEmplissage).build();
    }
    
    @GET
    @Path("statutBonLivraison")
    public Response getStatutBonLivraison() {
    	List<StatutBonLivraison> statutsBonLivraison = Arrays.asList(StatutBonLivraison.values());
        return Response.ok(statutsBonLivraison).build();
    }
    
    @GET
    @Path("typeBonOL")
    public Response getTypeBon() {
    	List<TypeBon> typesBon = Arrays.asList(TypeBon.values());
        return Response.ok(typesBon).build();
    }

    @GET
    @Path("natureProduit")
    public Response getAllNatureProduit() {
    	List<NatureProduit> natureProduitList = Arrays.asList(NatureProduit.values());
        return Response.ok(natureProduitList).build();
    }
    
    @GET
    @Path("typeChargement")
    public Response getAllTypeChargement() {
    	List<TypeChargement> typeChargementList = Arrays.asList(TypeChargement.values());
        return Response.ok(typeChargementList).build();
    }

    @GET
    @Path("typeCamion")
    public Response getAllCamionTypes() {
    	List<TypeCamion> siteTypeList = Arrays.asList(TypeCamion.values());
        return Response.ok(siteTypeList).build();
    }
    
    @GET
    @Path("categorieTypeFileAttente")
    public Response getAllCategorieTypeFileAttente() {
    	List<CategorieTypeFileAttente> siteTypeList = Arrays.asList(CategorieTypeFileAttente.values());
    	return Response.ok(siteTypeList).build();
    }

    @GET
    @Path("objet")
    public Response getObjet() {
        List<Objet> objetList = Arrays.asList(Objet.values());
        return Response.ok(objetList).build();
    }
    @GET
    @Path("profilType")
    public Response getProfilType() {
        List<ProfilType> profilTypeList = Arrays.asList(ProfilType.values());
        return Response.ok(profilTypeList).build();
    }

    @GET
    @Path("pieceReglementStatut")
    public Response getPieceReglementStatut() {
        List<StatutPieceReglement> statutPieceReglementList = Arrays.asList(StatutPieceReglement.values());
        return Response.ok(statutPieceReglementList).build();
    }
}
