package com.github.otr.home_lib.upload_management.application.port.in;

import com.github.otr.home_lib.upload_management.application.port.out.BinaryFileOutputPort;
import com.github.otr.home_lib.upload_management.application.use_case.RetrieveBinaryFileUseCase;
import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

/**
 *
 */
public class BinaryFileInputPort implements RetrieveBinaryFileUseCase {

    private final BinaryFileOutputPort binaryFileOutputPort;

    public BinaryFileInputPort(BinaryFileOutputPort binaryFileOutputPort) {
        this.binaryFileOutputPort = binaryFileOutputPort;
    }

    @Override
    public BinaryFile retrieveBinaryFile(String uuId) {
        BinaryFile binaryFile = binaryFileOutputPort
            .retrieveBinaryFileById(uuId);
        return null;
    }

    public boolean storeBinaryFile(BinaryFile binaryFile) {
        boolean result = binaryFileOutputPort.storeBinaryFile(binaryFile);
        return result;
    }

}
