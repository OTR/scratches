package com.github.otr.home_lib.upload_management.domain.entity;

import java.util.UUID;

/**
 * `id`  A unique identifier for each ebook file.
 * `filename` The original filename of the uploaded EPUB file.
 * `fileContent` The binary data of the EPUB file.
 */
public class BinaryFile {

    private String id;
    private String filename;
    private byte[] fileContent;

    public BinaryFile(String filename, byte[] fileContent) {
        this.id = UUID.nameUUIDFromBytes(fileContent).toString(); // TODO: Move into value object
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public String getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

}
