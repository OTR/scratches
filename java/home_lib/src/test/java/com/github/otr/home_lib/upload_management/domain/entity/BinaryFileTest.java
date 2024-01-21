package com.github.otr.home_lib.upload_management.domain.entity;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

/**
 *
 */
public class BinaryFileTest {

    /**
     * test #1
     */
    @Test
    public void constructor_setsFilenameAndFileContentCorrectly() {
        String filename = "test.epub";
        byte[] fileContent = new byte[] {1, 2, 3};
        BinaryFile file = new BinaryFile(filename, fileContent);

        assertEquals(filename, file.getFilename());
        assertArrayEquals(fileContent, file.getFileContent());
    }

    /**
     * test #2
     */
    @Test
    public void constructor_generatesUUIDBasedOnFileContent() {
        byte[] some = new byte[] {1, 2, 3};
        byte[] same = new byte[] {1, 2, 3};
        byte[] another = new byte[] {4, 5, 6};

        BinaryFile someF = new BinaryFile("file1.epub", some);
        BinaryFile sameF = new BinaryFile("file2.epub", same);
        BinaryFile anotherF = new BinaryFile("file3.epub", another);

        // Same UUID for identical content
        assertEquals(someF.getId(), sameF.getId());
        // Different UUIDs for different content
        assertNotEquals(someF.getId(), anotherF.getId());
    }

    /**
     * test #3
     */
    @Test
    public void constructor_throwsExceptionForEmptyFilename() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BinaryFile("", new byte[] {})
        );
    }

    /**
     * test #4
     */
    @Test
    public void constructor_throwsExceptionForNullFileContent() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BinaryFile("test.epub", null)
        );
    }

    /**
     * test #5
     */
    @Test
    public void getters_returnExpectedValues() {
        String filename = "test.epub";
        byte[] fileContent = new byte[] {1, 2, 3};
        BinaryFile file = new BinaryFile(filename, fileContent);

        assertEquals(filename, file.getFilename());
        assertArrayEquals(fileContent, file.getFileContent());
    }

}
