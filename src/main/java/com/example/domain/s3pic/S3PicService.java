package com.example.domain.s3pic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Slf4j
@ApplicationScoped
public class S3PicService {


    public enum WhatPicture {
        THUMBNAIL,

        ORIGINAL;
    }

    @Inject
    S3UploadService s3UploadService;

    public String finishPicture(byte[] bigPicture, String userUUID, String postUUID, String whatPicture) {

        //einmal checken ob die Ordner voorhanden sind
        if (s3UploadService.doesObjectExist()) {

            s3UploadService.checkFolders(userUUID, postUUID);
        }

        if (WhatPicture.ORIGINAL.name().equals(whatPicture)) {

            return s3UploadService.uploadToS3(bigPicture, WhatPicture.ORIGINAL.name().toLowerCase());

        } else if (WhatPicture.THUMBNAIL.name().equals(WhatPicture.THUMBNAIL.name().toLowerCase())) {

            return s3UploadService.uploadToS3(createThumbnail(bigPicture), "thumbnail");

        } else {

            //TODO Logic implementieren

            return "";
        }


    }


    public byte[] createThumbnail(byte[] imageData) {
        ByteArrayInputStream input = new ByteArrayInputStream(imageData);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            //TODO width?
            Thumbnails.of(input)
//                    .height()
                    .width(100)
                    .outputFormat("JPEG")
                    .toOutputStream(output);
        } catch (Exception e) {
            log.error("Thumbnail not generated");

            return imageData;
        }

        return output.toByteArray();
    }


}
