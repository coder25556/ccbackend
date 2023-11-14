package com.example.domain.post;

import com.example.domain.DBService.DBService;
import com.example.domain.model.DB.PostDB;
import com.example.domain.model.request.PostRequest;
//import com.example.domain.s3pic.S3PicService;
import com.example.domain.model.request.UserResponse;
import com.example.domain.model.response.PostResponse;
import com.example.domain.s3pic.S3PicService;
import com.example.domain.util.JWTDataExtractor;
import com.example.domain.util.Timestamp;
import com.example.domain.util.Uuid;
//import com.example.domain.util.aws.DynamoDB;
import com.example.domain.util.aws.CognitoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Base64;

@ApplicationScoped
public class PostService {


    @Inject
    DBService dbService;
    String tableName = "post_table";

    @Inject
    CognitoService cognitoService;

//    @Inject
//    S3PicService s3PicService;


    public void addPost(PostRequest postRequest, JsonWebToken jwt) {


        String userUUID = JWTDataExtractor.getUUID(jwt);

        PostDB db = new PostDB();

        //generate PostId
        db.setPostId(Uuid.generateUUID());

        //caption
        db.setCaption(postRequest.getCaption());

        //generate Timestamp
        db.setTimestampCreated(Timestamp.timestamp());

        //get UUID
        db.setCreatedFrom(userUUID);

        //get OrginalPicURL
//        db.setBigPictureUrl(s3PicService.finishPicture(decodePicture(postRequest.getPicture()), userUUID, db.getPostId(), S3PicService.WhatPicture.ORIGINAL.name()));
//
//
//        //getthumbanilURL
//        db.setThumbnailUrl(s3PicService.finishPicture(decodePicture(postRequest.getPicture()), userUUID, db.getPostId(), S3PicService.WhatPicture.THUMBNAIL.name()));


        //LIKes hier nicht nötig


        dbService.addItemToDB(db, tableName, PostDB.class);
    }

    public PostResponse getAllPosts(JsonWebToken jsonWebToken){


        PostResponse postResponse= new PostResponse();
        UserResponse userResponse= cognitoService.getUserInfos(jsonWebToken.toString());


        postResponse.setPosts(dbService.getAllPreview(JWTDataExtractor.getUUID(jsonWebToken),tableName));

        postResponse.setUsername(JWTDataExtractor.getUsername(jsonWebToken));

        postResponse.setDisplayname(userResponse.getDisplayName());
        postResponse.setEmail(userResponse.getEmail());




        return postResponse;
    }


    private byte[] decodePicture(String picture) {
        return Base64.getDecoder().decode(picture);

    }



}
