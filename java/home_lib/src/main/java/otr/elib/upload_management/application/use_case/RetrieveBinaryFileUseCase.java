package otr.elib.upload_management.application.use_case;

import otr.elib.upload_management.domain.entity.BinaryFile;

/**
 *
 */
public interface RetrieveBinaryFileUseCase {

    BinaryFile retrieveBinaryFile(String uuId);

}
