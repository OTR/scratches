# The reference policy to be refactored

```java
public class SomePolicy {

    private static String getFilenameWithoutExt(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1) {
            fileName = fileName.substring(0, lastIndex);
        }
        return fileName;
    }

    public static String normalize(String input) {
        // 0. Remove extension
        input = getFilenameWithoutExt(input);
        
        // 1. To lower case
        input = input.toLowerCase();
        
        // 2. Replace all the spaces with underscore
        input = input.replaceAll("[^a-zA-Z_\\- ]", "");
        input = input.replaceAll(" ", "_");
        input = input.replaceAll("-", "_");
        
        // 3. Replace multiple underscores with single underscore
        input = input.replaceAll("_+", "_");
        
        // 4. Trim leading and trailing underscores
        input = input.replaceAll("_", " ");
        input = input.strip();
        input = input.replaceAll(" ", "_");

        return input;
    }

}
```