package com.github.otr.home_lib.upload_management.application.use_case;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

/**
 *
 */
public interface RetrieveBinaryFileUseCase {

    BinaryFile retrieveBinaryFile(String uuId);

}
