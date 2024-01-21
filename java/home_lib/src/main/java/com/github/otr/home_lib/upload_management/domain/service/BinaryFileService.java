package com.github.otr.home_lib.upload_management.domain.service;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;


interface BinaryFileService {
    void storeBinaryFile(BinaryFile file);

    BinaryFile retrieveBinaryFileById(String id);

}
