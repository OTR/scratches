package com.github.otr.home_lib.upload_management.test.steps;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BinaryFileSteps {
    private String filename;
    private byte[] fileContent;
    private BinaryFile binaryFile;
    private String generatedUUID;

//______________________________________________________________________________
//    1 TEST CASE

    @Given("I have a filename {string}")
    public void aFilename(String filename) {
        this.filename = filename;
    }

    @Given("^I have a file with content \\[([^\\]]*)\\]$")
    public void fileContentIs(String content) {
        String[] contentArray = content.split(", ");
        this.fileContent = new byte[contentArray.length];
        for (int i = 0; i < contentArray.length; i++) {
            this.fileContent[i] = Byte.parseByte(contentArray[i]);
        }
    }

    @When("I create a BinaryFile object")
    public void iCreateABinaryFileObject() {
        this.binaryFile = new BinaryFile(filename, fileContent);
        generatedUUID = binaryFile.getId();
    }

     @Then("the filename should be {string}")
    public void theFilenameShouldBe(String expectedFilename) {
        assertEquals(expectedFilename, binaryFile.getFilename());
    }

    @Then("^the file content should be \\[([^\\]]*)\\]$")
    public void theFileContentShouldBe(String expectedContent) {
        String[] expectedContentArray = expectedContent.split(", ");
        byte[] expectedContentBytes = new byte[expectedContentArray.length];
        for (int i = 0; i < expectedContentArray.length; i++) {
            expectedContentBytes[i] = Byte.parseByte(expectedContentArray[i]);
        }
        assertArrayEquals(expectedContentBytes, binaryFile.getFileContent());
    }

    @And("the UUID should be generated based on the file content") // TODO: provide initial array
    public void theUUIDShouldBeGeneratedBasedOnTheFileContent() {
        assertEquals(binaryFile.getId(), generatedUUID); // TODO: call UUID builder with initial array
    }

//______________________________________________________________________________
//    2 TEST CASE

    private BinaryFile file1;
    private BinaryFile file2;
    private BinaryFile file3;

    private byte[] fileContentSame;
    private byte[] fileContentDifferent;

    @Given("^I have identical file content \\[([^\\]]*)\\]$")
    public void identicalFileContentIs(String content) {
        String[] contentArray = content.split(", ");
        this.fileContentSame = new byte[contentArray.length];
        for (int i = 0; i < contentArray.length; i++) {
            this.fileContentSame[i] = Byte.parseByte(contentArray[i]);
        }
    }

    @Given("^I have different file content \\[([^\\]]*)\\]$")
    public void differentFileContentIs(String content) {
        String[] contentArray = content.split(", ");
        this.fileContentDifferent = new byte[contentArray.length];
        for (int i = 0; i < contentArray.length; i++) {
            this.fileContentDifferent[i] = Byte.parseByte(contentArray[i]);
        }
    }

    @When("^I create a BinaryFile with filename \"([^\"]*)\" and content \\[([^\\]]*)\\]$")
    public void iCreateABinaryFileWithFilenameAndContent(String filename, String content) {
        file1 = new BinaryFile(filename, fileContent);
    }

    @When("^I create another BinaryFile with filename \"([^\"]*)\" and content \\[([^\\]]*)\\]$")
    public void iCreateAnotherBinaryFileWithFilenameAndContent(String filename, String content) {
        file2 = new BinaryFile(filename, fileContentSame);
    }

    @When("^I create a different BinaryFile with filename \"([^\"]*)\" and content \\[([^\\]]*)\\]$")
    public void iCreateADifferentBinaryFileWithFilenameAndContent(String filename, String content) {
        file3 = new BinaryFile(filename, fileContentDifferent);
    }

    @Then("^the UUID of the first file should be the same as the second file$")
    public void theUUIDOfTheFirstFileShouldBeTheSameAsTheSecondFile() {
        assertEquals(file1.getId(), file2.getId());
    }

    @Then("^the UUID of the first file should not be the same as the third file$")
    public void theUUIDOfTheFirstFileShouldNotBeTheSameAsTheThirdFile() {
        assertNotEquals(file1.getId(), file3.getId());
    }

//______________________________________________________________________________
//    TEST CASE 3
    private Exception exception;

    @When("^I try to create a BinaryFile with an empty filename and content \\[([^\\]]*)\\]$")
    public void iTryToCreateABinaryFileWithAnEmptyFilenameAndContent(String content) {
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> binaryFile = new BinaryFile("", fileContent)
        );
    }

    @Then("^an IllegalArgumentException should be thrown with message \"([^\"]*)\"$")
    public void anIllegalArgumentExceptionShouldBeThrown(String expectedMessage) {
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

//______________________________________________________________________________
//    TEST CASE 4

    @When("^I try to create a BinaryFile with a null file content$")
    public void iTryToCreateABinaryFileWithANullFileContent() {
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> binaryFile = new BinaryFile(filename, null)
        );
    }

//______________________________________________________________________________
//    TEST CASE 5

    @Given("^I have a BinaryFile with filename \"([^\"]*)\" and content \\[([^\\]]*)\\]$")
    public void iHaveABinaryFileWithFilenameAndContent(String filename, String content) {
        this.filename = filename;
        String[] contentArray = content.split(", ");
        this.fileContent = new byte[contentArray.length];
        for (int i = 0; i < contentArray.length; i++) {
            this.fileContent[i] = Byte.parseByte(contentArray[i]);
        }
        this.binaryFile = new BinaryFile(filename, fileContent);
    }

}
