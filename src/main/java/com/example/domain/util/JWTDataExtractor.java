package com.example.domain.util;

import jakarta.json.Json;
import org.eclipse.microprofile.jwt.JsonWebToken;

public class JWTDataExtractor {

    public static String getUUID(JsonWebToken jwtAuth) {
        //getCognitoUserId
        System.out.println((char[]) jwtAuth.getClaim("username"));

        return jwtAuth.getClaim("sub");
    }

    public static String getUsername(JsonWebToken jwtAuth){

        return jwtAuth.getClaim("username");
    }

}
