package com.example.domain.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequest {

    String picture;

    String caption;

}
