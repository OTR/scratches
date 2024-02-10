package otr.elib.upload_management.domain.specification;

/**
 *
 */
public class FileContentNotNullSpecification extends AbstractSpecification<byte[]> {

    @Override
    public boolean isSatisfiedBy(byte[] fileContent) {
        return fileContent != null;
    }

}
