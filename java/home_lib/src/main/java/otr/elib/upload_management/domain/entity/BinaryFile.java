package otr.elib.upload_management.domain.entity;

import otr.elib.upload_management.domain.specification.FileContentNotNullSpecification;
import otr.elib.upload_management.domain.specification.FilenameNotEmptySpecification;

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

        var notNullSpec = new FileContentNotNullSpecification();
        var notEmptyFilenameSpec = new FilenameNotEmptySpecification();

        if (!notNullSpec.isSatisfiedBy(fileContent)) {
            throw new IllegalArgumentException("file content should not be null");
        }

        if (!notEmptyFilenameSpec.isSatisfiedBy(filename)) {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }

        this.id = UUID.nameUUIDFromBytes(fileContent).toString(); // TODO: Move into value object
        this.filename = filename;
        this.fileContent = fileContent;
    }

    private BinaryFile(String id, String filename, byte[] fileContent) {
        this.id = id;
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public static BinaryFile fromDbo(
            String id,
            String filename,
            byte[] fileContent
    ) {
        return new BinaryFile(id, filename, fileContent);
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
