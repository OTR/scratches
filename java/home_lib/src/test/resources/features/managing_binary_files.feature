Feature: Managing Binary Files

# test #2
  Scenario Outline: Ensuring Unique UUID for Identical Content
    Given I have two files with the same content <content>
    When I create BinaryFile objects for both files
    Then the UUIDs of the files should be the same

    Examples:
      | content |
      | "1, 2, 3" |
      | "some text" |

# test #3
  Scenario: Validating Input for Constructor
    Given I try to create a BinaryFile with an empty filename
    When I call the BinaryFile constructor
    Then an IllegalArgumentException should be thrown

# test #4
  Scenario: Validating Input for Constructor
    Given I try to create a BinaryFile with null file content
    When I call the BinaryFile constructor
    Then an IllegalArgumentException should be thrown
