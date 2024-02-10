package otr.elib.upload_management.application.port.out;

import otr.elib.upload_management.domain.entity.BinaryFile;

/**
 * ex BinaryFilePersistencePort
 */
public interface BinaryFileOutputPort {

    boolean storeBinaryFile(BinaryFile file);

    BinaryFile retrieveBinaryFileById(String uuId);

}
