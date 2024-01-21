package com.github.otr.home_lib.upload_management.framework.adapter.output.postres.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import java.util.UUID;
import java.io.Serializable;

/**
 *
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "binary_file")
@Converter(name="uuidConverter", converterClass=UUIDTypeConverter.class)
public class BinaryFileDbo implements Serializable {

    @Id
    @Column(
            name="binary_file_id",
            columnDefinition = "uuid",
            updatable = false
    )
    @Convert("uuidConverter")
    private UUID binaryFileId;

    @Column(
            name="filename",
            nullable = false
    )
    private String filename;

    @Column(
            name="file_content",
            nullable = false
    )
    private byte[] fileContent;

}
