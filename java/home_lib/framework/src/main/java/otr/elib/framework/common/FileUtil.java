package otr.elib.framework.common;

import otr.elib.framework.exception.FileNotFoundAppException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static String readTextFile(String filePath) {

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
            throw new RuntimeException(e);
        }

    }

}
