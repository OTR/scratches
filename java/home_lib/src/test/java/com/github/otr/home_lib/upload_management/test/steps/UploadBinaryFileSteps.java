package com.github.otr.home_lib.upload_management.test.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.github.otr.home_lib.upload_management.application.use_case.UploadBinaryFileUseCase;

/**
 *
 */
public class UploadBinaryFileSteps {

    private final UploadBinaryFileUseCase uploadUseCase;

    public UploadBinaryFileSteps(UploadBinaryFileUseCase uploadUseCase) {
        this.uploadUseCase = uploadUseCase;
    }

    @Given("I have a file named {string}")
    public void iHaveAFileNamed(String filename) {
        // Create a BinaryFile object with the given filename (and potentially file content)
    }

    @When("I upload the file")
    public void iUploadTheFile() {
        // Call the uploadUseCase.uploadBinaryFile(file) method
    }

    @Then("The file should be stored")
    public void theFileShouldBeStored() {
        // Assert that the file has been successfully stored (using a mock or test double for persistence)
    }

}
