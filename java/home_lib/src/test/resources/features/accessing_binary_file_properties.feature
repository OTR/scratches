Feature: Accessing Binary File Properties

  # test #5
  Scenario Outline: Accessing File Properties
    Given I have a BinaryFile with filename "test.epub" and content <content>
    When I retrieve the filename
    Then the filename should be "test.epub"
    When I retrieve the file content
    Then the file content should be <content>

    Examples:
      | content       |
      | "some text"   |
      | "image data"  |
      | "binary data" |
