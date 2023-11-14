package com.example.domain.model.DB;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDB {

    @JsonProperty("user-id")
    String user_id;
    String username;

}
