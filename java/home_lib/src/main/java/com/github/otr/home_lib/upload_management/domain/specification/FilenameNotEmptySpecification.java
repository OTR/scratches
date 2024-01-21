package com.github.otr.home_lib.upload_management.domain.specification;

/**
 *
 */
public class FilenameNotEmptySpecification extends AbstractSpecification<String> {

    @Override
    public boolean isSatisfiedBy(String filename) {
        return !filename.isEmpty();
    }

}
