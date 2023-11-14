package com.example.domain.s3pic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

@Slf4j
@ApplicationScoped
public class S3UploadService {

    @Inject
    S3Client s3Client;

    final String bucketName = "post-pictures";


    String picturePath;

    public void checkFolders(String userUUID, String postUUID) {


        this.picturePath = userUUID + "/" + postUUID + "/";

        //folder generating User
        createFolder(userUUID + "/");

        //folder generating Post

        createFolder(this.picturePath);
        ;
    }


    public String uploadToS3(byte[] picture, String bigOrThumbnail) {

        String key = this.picturePath + bigOrThumbnail;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(picture));


        //TODO hochgeladen

        log.info(key + " wurde hochgeladen");
        return key;
    }

    private boolean doesFolderExist(String folderKey) {
        ListObjectsV2Request listReq = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(folderKey)
                .delimiter("/")
                .build();

        ListObjectsV2Response listRes = s3Client.listObjectsV2(listReq);
        return listRes.contents().stream().anyMatch(s3Object -> s3Object.key().equals(folderKey));
    }

    private void createFolder(String folderKey) {
        if (!doesFolderExist(folderKey)) {
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderKey)
                    .build(), RequestBody.empty());

            log.info("Order " + folderKey + "wurde erstellt");
        } else {

            log.info("Order " + folderKey + "wurde nicht erstellt - schon vorhanden");
        }
    }

    public boolean doesObjectExist() {
        try {
            s3Client.headObject(HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(this.picturePath)
                    .build());
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        }
    }
}
