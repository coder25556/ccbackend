package com.example.domain.model.request;

import com.example.domain.model.response.PostPreview;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {


    //cognito sub
    String uuid;


    //Cognito-Name
    String displayName;

    //cognito email
    String email;


    //TODO Cognito Username suchen wie das im Token steht
    String username;

    PostPreview[] posts;

}
