package com.example.domain.util.aws;

import com.example.domain.model.request.UserResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;

import java.util.List;

@ApplicationScoped
public class CognitoService {

    @ConfigProperty(name = "aws.region")
    String region;


    AdminGetUserRequest getUserRequest;
    CognitoIdentityProviderClient cognitoClient;
    //CC-Pool
    String userPoolId = "us-east-1_vnfE66yLP";


    private void init() {

        cognitoClient = CognitoIdentityProviderClient.builder()
                .httpClient(ApacheHttpClient.builder().build())
                .region(Region.of(region)) // Ersetzen Sie "region" durch Ihre Region
                .build();

    }

    public UserResponse getUserInfos(String accessToken) {
        init();
        GetUserRequest getUserRequest = GetUserRequest.builder()
                .accessToken(accessToken) // Access Token des authentifizierten Benutzers
                .build();

        GetUserResponse getUserResponse = cognitoClient.getUser(getUserRequest);


        List<AttributeType> userAttributes = getUserResponse.userAttributes();

        UserResponse userResponse = new UserResponse();

        for (AttributeType attribute : userAttributes) {
            switch (attribute.name()) {
                case "sub":
                    userResponse.setUuid(attribute.value());
                    break;
                case "name":
                    userResponse.setDisplayName(attribute.value());
                    break;
                case "email":
                    userResponse.setEmail(attribute.value());
                    break;
                case "preferred_username":
                    userResponse.setUsername(attribute.value());
                    break;
                // Add other cases as needed
            }
        }


        return userResponse;

    }

}
