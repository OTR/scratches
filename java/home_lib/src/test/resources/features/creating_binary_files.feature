Feature: Creating Binary Files

# test #1
  Scenario: Creating a Binary File
    Given I have a file named "test.epub" with content
    When I create a BinaryFile object
    Then the filename should be "test.epub"
    And the file content should be accessible
    And the UUID should be generated based on the file content
