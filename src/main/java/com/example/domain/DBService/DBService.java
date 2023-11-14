package com.example.domain.DBService;

import com.example.domain.model.DB.PostDB;
import com.example.domain.model.response.PostPreview;
import com.example.domain.util.aws.DynamoDB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApplicationScoped
public class DBService {


    @Inject
    DynamoDB dynamoDB;

    DynamoDbEnhancedClient dynamoDbEnhancedClient;


    public <T> void addItemToDB(T item, String tablename,Class<T> class2){

        this.dynamoDbEnhancedClient = dynamoDB.startDynamoDBTest();

        DynamoDbTable<T> table = this.dynamoDbEnhancedClient.table(tablename, TableSchema.fromBean(class2));

        table.putItem(item);

    }

    public PostPreview[] getAllPreview(String userid, String tablename2){

        this.dynamoDbEnhancedClient = dynamoDB.startDynamoDBTest();

        DynamoDbTable<PostDB> table = this.dynamoDbEnhancedClient.table(tablename2, TableSchema.fromBean(PostDB.class));

        ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                .filterExpression(Expression.builder()
                        .expression("createdFrom = :userid")
                        .putExpressionValue(":userid", AttributeValue.builder().s(userid).build())
                        .build())
                .build();


        List<PostDB> postDBS=table.scan(scanRequest)
                .items()
                .stream()
                .toList();

        return postDBS.stream()
                .map(e -> PostPreview.builder()
                        .postId(e.getPostId())
                        .caption(e.getCaption())
                        .timestampCreation(e.getTimestampCreated())
                        .thumbnailUrl(e.getThumbnailUrl())
                        .build()) // Make sure to call build() to create PostPreview objects
                .toArray(PostPreview[]::new);

    }

}
