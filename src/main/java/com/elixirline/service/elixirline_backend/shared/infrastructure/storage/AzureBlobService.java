package com.elixirline.service.elixirline_backend.shared.infrastructure.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AzureBlobService {
    private final BlobContainerClient containerClient;

    public AzureBlobService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName) {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

        containerClient = serviceClient.getBlobContainerClient(containerName);


        if (!containerClient.exists()) {
            containerClient.create();
        }
    }

    public String upload(MultipartFile file) {
        try {

            String blobName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            BlobClient blobClient = containerClient.getBlobClient(blobName);


            blobClient.upload(file.getInputStream(), file.getSize(), true);

            BlobHttpHeaders headers = new BlobHttpHeaders()
                    .setContentType(file.getContentType())
                    .setContentDisposition("inline");

            blobClient.setHttpHeaders(headers);


            return blobClient.getBlobUrl();
        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen a Azure Blob Storage", e);
        }
    }
}
