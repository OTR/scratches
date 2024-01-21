package com.github.otr.home_lib.upload_management.framework.adapter.output.postres.mapper;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;
import com.github.otr.home_lib.upload_management.framework.adapter.output.postres.data.BinaryFileDbo;

import java.util.UUID;

/**
 *
 */
public class BinaryFileMapper {

    public static BinaryFileDbo toDbo(BinaryFile entity) {
        var uuId = UUID.fromString(entity.getId());
        var filename = entity.getFilename();
        var fileContent = entity.getFileContent();

        return new BinaryFileDbo(uuId, filename, fileContent);
    }

    public static BinaryFile toDomain(BinaryFileDbo dbo) {
        var uuId = dbo.getUuId().toString();
        var filename = dbo.getFilename();
        var fileContent = dbo.getFileContent();

        return BinaryFile.fromDbo(uuId, filename, fileContent);
    }

}
