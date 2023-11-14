package com.example.domain.model.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {


    String userId;


    String displayname;
    String email;

    String username;

    PostPreview[] posts;

}
