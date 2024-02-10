package otr.elib.upload_management.domain.service;

import otr.elib.upload_management.domain.entity.BinaryFile;


interface BinaryFileService {
    void storeBinaryFile(BinaryFile file);

    BinaryFile retrieveBinaryFileById(String id);

}
