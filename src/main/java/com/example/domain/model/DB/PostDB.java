package com.example.domain.model.DB;


import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;


@DynamoDbBean
@NoArgsConstructor
@Data
@Builder
public class PostDB {

    String postId;

    String caption;

    String thumbnailUrl;

    String bigPictureUrl;

    String timestampCreated;

    String createdFrom;

    List<String> likedFrom;


    public PostDB(String postId, String caption, String thumbnailUrl, String bigPictureUrl, String timestampCreated, String createdFrom, List<String> likedFrom) {
        this.postId = postId;
        this.caption = caption;
        this.thumbnailUrl = thumbnailUrl;
        this.bigPictureUrl = bigPictureUrl;
        this.timestampCreated = timestampCreated;
        this.createdFrom = createdFrom;
        this.likedFrom = likedFrom;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("postId")
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @DynamoDbAttribute("caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @DynamoDbAttribute("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @DynamoDbAttribute("bigPictureUrl")
    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    @DynamoDbAttribute("timestampCreated")
    public String getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(String timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    @DynamoDbAttribute("createdFrom")
    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    @DynamoDbAttribute("likedFrom")
    public List<String> getLikedFrom() {
        return likedFrom;
    }

    public void setLikedFrom(List<String> likedFrom) {
        this.likedFrom = likedFrom;
    }
}
