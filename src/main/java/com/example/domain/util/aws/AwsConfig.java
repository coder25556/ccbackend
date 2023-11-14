package com.example.domain.util.aws;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@ConfigProperties(prefix = "aws")
@ApplicationScoped
public class AwsConfig {

    String accessKeyId;
    String secretAccessKey;

    public StaticCredentialsProvider toStaticCredentialsProvider() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return StaticCredentialsProvider.create(awsCreds);
    }
}
