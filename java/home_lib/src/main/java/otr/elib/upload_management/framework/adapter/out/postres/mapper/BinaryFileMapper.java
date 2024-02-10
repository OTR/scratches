package otr.elib.upload_management.framework.adapter.out.postres.mapper;

import otr.elib.upload_management.domain.entity.BinaryFile;
import otr.elib.upload_management.framework.adapter.out.postres.data.BinaryFileDbo;

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
