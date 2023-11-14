package com.example.domain.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostPreview {


    String thumbnailUrl;

    String caption;

    String timestampCreation;

    String postId;

}
