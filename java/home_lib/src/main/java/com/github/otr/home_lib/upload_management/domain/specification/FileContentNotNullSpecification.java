package com.github.otr.home_lib.upload_management.domain.specification;

/**
 *
 */
public class FileContentNotNullSpecification extends AbstractSpecification<byte[]> {

    @Override
    public boolean isSatisfiedBy(byte[] fileContent) {
        return fileContent != null;
    }

}
