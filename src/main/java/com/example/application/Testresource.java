package com.example.application;

import com.example.domain.util.aws.CognitoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
@Path("/test")
public class Testresource {

    @Inject
    JsonWebToken jsonWebToken;
    @Inject
    CognitoService cognito;


    @GET
    public void test(@HeaderParam("Authorization") String accessToken) {


        cognito.getUserInfos(accessToken);


    }
}
