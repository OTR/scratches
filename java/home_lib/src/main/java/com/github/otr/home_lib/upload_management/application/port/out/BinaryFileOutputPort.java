package com.github.otr.home_lib.upload_management.application.port.out;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

/**
 * ex BinaryFilePersistencePort
 */
public interface BinaryFileOutputPort {

    boolean storeBinaryFile(BinaryFile file);

    BinaryFile retrieveBinaryFileById(String uuId);

}
