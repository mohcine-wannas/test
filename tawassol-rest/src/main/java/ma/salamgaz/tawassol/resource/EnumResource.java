package ma.salamgaz.tawassol.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import ma.salamgaz.tawassol.admin.model.enums.SiteType;
import ma.salamgaz.tawassol.common.model.enums.CategorieTypeFileAttente;
import ma.salamgaz.tawassol.common.model.enums.NatureProduit;
import ma.salamgaz.tawassol.common.model.enums.TypeCamion;
import ma.salamgaz.tawassol.common.model.enums.TypeChargement;

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

}
