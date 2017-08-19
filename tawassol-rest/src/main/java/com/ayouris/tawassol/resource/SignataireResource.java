package com.ayouris.tawassol.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SignataireService;

/**
 * 
 * @author m.wannas
 *
 */

@Component("signataireResource")
@Path("/signataire")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignataireResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignataireResource.class);

    @Autowired
    private SignataireService signataireService;

    @Autowired
    public SignataireResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.signataireService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    @JsonView(CommonView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.signataireService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    @JsonView(CommonView.class)
    public Response remove(@PathParam("id") Long id) {
        this.signataireService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(CommonView.class)
    public Response create(SignataireBean object) {
        try {
            return Response.ok(this.signataireService.createOrUpdate( object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.SIGNATAIRE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
        return Response.ok(this.signataireService.getAll()).build();
    }

    @POST
    @Path("{id}/upload") 
    @JsonView(CommonView.class)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id,@Multipart("file") Attachment attachment) {
	   try {
	        InputStream in = attachment.getObject(InputStream.class);
	        this.signataireService.saveSignature(id,in);
		    return Response.ok(id).build();

	   } catch (Exception ex) {
	       LOGGER.error("uploadFile.error():", ex);
	       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	   }
   }
    
    @GET
    @Path("/{id}/signature")
    public Response getImage(@PathParam("id") Long id) {
   		File file =  this.signataireService.getSignature(id);
		byte[] bytes;
		try {
			bytes = loadFile(file);

		byte[] encoded = Base64.encode(bytes);
		String encodedString = new String(encoded);
		Map<String,String> result = new HashMap<String,String>();
		result.put("img",encodedString);
   		return Response.ok(result).build();	
   		} catch (Exception e) {
   			LOGGER.error("uploadFile.error():", e);
	       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
    
    @SuppressWarnings("resource")
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}
}
