package com.example.application;

import com.example.domain.model.request.PostRequest;
import com.example.domain.post.PostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/post")
public class PostResource {

    @Inject
    PostService postService;

    @Inject
    JsonWebToken jwt;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPost(PostRequest post) {


        try {
            postService.addPost(post, jwt);

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(500).build();

        }
    }


    @Path("/userposts")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserPosts(@HeaderParam("Authorization") String accessToken) {


        try {
            postService.getAllPosts(jwt);

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(500).build();

        }
    }
}
