package com.example.application;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class UserResource {

    @Inject
    JsonWebToken jwt;


    @GET
    @Path("/method")
    @RolesAllowed("ROLE_USER") // Annahme: Deine Cognito-Rolle heißt 'ROLE_USER'
    public Response getUser() {
        // Deine Logik hier
        return Response.ok().entity("Zugriff gewährt!").build();
    }


}
