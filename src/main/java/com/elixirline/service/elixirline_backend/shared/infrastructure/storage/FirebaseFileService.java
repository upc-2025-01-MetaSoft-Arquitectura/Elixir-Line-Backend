package com.elixirline.service.elixirline_backend.shared.infrastructure.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FirebaseFileService {
    @Value("${firebase.credentials}")
    private String firebaseCredentials;
    private Storage storage;
    private static final Logger logger = LoggerFactory.getLogger(FirebaseFileService.class);

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            InputStream serviceAccount = new ByteArrayInputStream(firebaseCredentials.getBytes(StandardCharsets.UTF_8));
            storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("pointbar-application")
                    .build()
                    .getService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            String imageName = UUID.randomUUID().toString();
            String objectName = "images/profileimages/" + imageName + "." + getExtension(file.getOriginalFilename());

            Map<String, String> metadata = new HashMap<>();
            metadata.put("firebaseStorageDownloadTokens", imageName);

            BlobId blobId = BlobId.of("pointbar-application.appspot.com", objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .setMetadata(metadata)
                    .build();

            storage.create(blobInfo, file.getInputStream().readAllBytes());

            String publicUrl = "https://firebasestorage.googleapis.com/v0/b/"
                    + "pointbar-application.appspot.com/o/"
                    + URLEncoder.encode(objectName, StandardCharsets.UTF_8)
                    + "?alt=media&token=" + imageName;

            return publicUrl;
        } catch (Exception e) {
            logger.error("Failed to upload image to Firebase Storage", e);
            throw new IOException("Failed to upload image to Firebase", e);
        }
    }


    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}