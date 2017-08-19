package com.ayouris.tawassol.security.resource;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ayouris.tawassol.common.model.bean.UserDataAuthenticate;

@Path("/auth")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_OCTET_STREAM })
@Produces(MediaType.APPLICATION_JSON)
public interface AuthenticateResource<T, V extends Serializable> {

    /**
     * @param username
     * @param password
     * @return token & user details
     */
    @POST
    @Path("authenticateForm")
    public Response authenticateForm(@FormParam("username") String username, @FormParam("password") char[] password);

    @POST
    @Path("authenticatePartner")
    public Response authenticatePartner(UserDataAuthenticate user);

    /**
     * 
     * @param user
     * @return token & user details
     */
    @POST
    @Path("authenticate")
    public Response authenticate(UserDataAuthenticate user);

    /**
     * validate and return user details
     * 
     * @param token
     * @return user details
     */
    @GET
    @Path("authenticateToken")
    public Response authenticateToken(@QueryParam("token") String token);

    /**
     * 
     * @param username
     * @return
     */
    // @GET
    // @Path("authenticateRemoteUser")
    public Response authenticateRemoteUser(@QueryParam("username") String username);

    @POST
    @Path("logout")
    public Response logout();

}
