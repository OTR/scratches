package otr.elib.upload_management.application.port.in;

import otr.elib.upload_management.application.port.out.BinaryFileOutputPort;
import otr.elib.upload_management.application.use_case.RetrieveBinaryFileUseCase;
import otr.elib.upload_management.domain.entity.BinaryFile;

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
