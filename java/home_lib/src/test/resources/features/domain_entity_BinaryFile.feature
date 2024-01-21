Feature: Creating Binary Files

#  Test #1

  Scenario: Creating a Binary File
    Given I have a filename "test.epub"
    And I have a file with content [1, 2, 3]
    When I create a BinaryFile object
    Then the filename should be "test.epub"
    And the file content should be [1, 2, 3]
    And the UUID should be generated based on the file content

# Test #2

  Scenario: Ensuring Unique UUID for Identical Content
    Given I have a file with content [1, 2, 3]
    And I have identical file content [1, 2, 3]
    And I have different file content [4, 5, 6]
    When I create a BinaryFile with filename "file1.epub" and content [1, 2, 3]
    And I create another BinaryFile with filename "file2.epub" and content [1, 2, 3]
    And I create a different BinaryFile with filename "file3.epub" and content [4, 5, 6]
    Then the UUID of the first file should be the same as the second file
    And the UUID of the first file should not be the same as the third file

# Test #3

  Scenario: Constructor throws exception for empty filename
    Given I have a file with content [1, 2, 3]
    When I try to create a BinaryFile with an empty filename and content [1, 2, 3]
    Then an IllegalArgumentException should be thrown with message "Filename cannot be empty."

# Test #4

  Scenario: Constructor throws exception for null file content
    Given I have a filename "test.epub"
    When I try to create a BinaryFile with a null file content
    Then an IllegalArgumentException should be thrown with message "file content should not be null"

# Test #5

  Scenario: Accessing File Properties
    Given I have a BinaryFile with filename "test.epub" and content [1, 2, 3]
    Then the filename should be "test.epub"
    And the file content should be [1, 2, 3]
