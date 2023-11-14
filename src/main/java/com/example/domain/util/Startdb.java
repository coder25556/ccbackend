//package com.example.domain.util;
//
//import com.example.domain.model.DB.PostDB;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
//import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//
//import java.net.URI;
//
//public class Startdb {
//
//    private DynamoDbClient client;
//
//    private DynamoDbTable<PostDB> produktTable;
//
//    private String DYNAMODB_TABLE_NAME = "table-post";
//
//
//    public void initDynamoDbClient() {
//        this.client = DynamoDbClient.builder()
//                .endpointOverride(URI.create("http://localhost:8000"))  // DynamoDB Local Endpunkt
//                .region(Region.EU_CENTRAL_1)
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummyAccessKey", "dummySecretKey")))
//                .build();
//
//        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(client)
//                .build();
//
//        produktTable = enhancedClient.table(DYNAMODB_TABLE_NAME, TableSchema.fromBean(PostDB.class));
//    }
//}
