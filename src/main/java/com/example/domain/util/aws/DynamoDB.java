package com.example.domain.util.aws;

import com.example.domain.model.DB.PostDB;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@ApplicationScoped
public class DynamoDB {

    AwsConfig awsConfig = new AwsConfig();

    @ConfigProperty(name = "aws.region")
    String region;

    private DynamoDbClient client;
    private DynamoDbTable<PostDB> produktTable;
    private String DYNAMODB_TABLE_NAME = "post_table";
    private Region REGION = Region.US_WEST_2;


    //Test
    public DynamoDbEnhancedClient startDynamoDBTest() {
        this.client = DynamoDbClient.builder()
                .httpClient(ApacheHttpClient.builder().build())
                .endpointOverride(URI.create("http://localhost:8000"))  // DynamoDB Local Endpunkt
                .region(REGION)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummyAccessKey", "dummySecretKey")))
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();



    }


    //real

//    public <T> DynamoDbEnhancedClient startDynamoDB() {
//        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
//                .region(Region.of(region))
//                .build();
//
//        return DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(dynamoDbClient)
//                .build();
//
//    }

}