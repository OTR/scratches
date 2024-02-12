package otr.elib.framework.common;

import otr.elib.domain.exception.BaseAppException;
import otr.elib.framework.exception.FileNotFoundAppException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtil {

    private static final String APP_DATA = ".app_data";

    public static String readTextFromFile(String filePath) {

        try (BufferedReader br = new BufferedReader(
            new FileReader(filePath)
        )) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundAppException(e);
        } catch (IOException e) {
            throw new BaseAppException(e);
        }

    }

    public static void saveTextToFile(String source, String filePath) {
        try {
            FileWriter outputFile = new FileWriter(filePath);
            outputFile.write(source);
            outputFile.close();
        } catch (IOException e) {
            throw new BaseAppException(e);
        }
    }

    public static String getAbsPathOfAppData(String filename) {
        String homeDir = getUnixHomeDir();
        Path fileInAppData = Path.of(homeDir)
            .resolve(APP_DATA)
            .resolve(filename)
            .toAbsolutePath();

        createDirectoryIfNotExists(fileInAppData.toFile().getParent());

        return fileInAppData.toString();
    }

    public static void createDirectoryIfNotExists(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (!result) {
                throw new BaseAppException(
                    "Failed to create directories: " +
                    directory.getAbsolutePath()
                );
            }
        }
    }

    private static String getUnixHomeDir() {
        return System.getProperty("user.home");
    }

}
