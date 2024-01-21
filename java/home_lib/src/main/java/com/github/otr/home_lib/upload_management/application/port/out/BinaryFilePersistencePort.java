package com.github.otr.home_lib.upload_management.application.port.out;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

public interface BinaryFilePersistencePort {

    void storeBinaryFile(BinaryFile file);

    BinaryFile retrieveBinaryFileById(String id);

}
