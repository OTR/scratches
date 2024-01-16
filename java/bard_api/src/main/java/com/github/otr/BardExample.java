package com.github.otr;

import com.pkslow.ai.AIClient;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.pkslow.ai.domain.AnswerStatus;

import java.io.IOException;
import java.io.InputStream;

import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

/*
https://www.pkslow.com/docs/en/java-library-google-bard/#the-example
 */
public class BardExample {
    private static final Logger log = Logger.getLogger("MAIN THREAD");

    public static void main(String[] args) throws IOException {
        InputStream stream = BardExample.class.getClassLoader()
                .getResourceAsStream("application.properties");
        Properties prop = new Properties();
        prop.load(stream);
        String token = prop.getProperty("BARD_KEY");
        System.out.println(token);

        AIClient client = new GoogleBardClient(token, Duration.ofMinutes(1));
        Answer answer = client.ask("What is the prompt engineering?");

        printChosenAnswer(answer);

    }

    private static void printChosenAnswer(Answer answer) {
        StringBuilder sb = new StringBuilder();
        if (answer.getStatus() == AnswerStatus.OK) {
            sb.append("\n### Chosen Answer\n");
            sb.append(answer.getChosenAnswer());
            log.info(String.format("Output: \n %s", sb));
        }
    }

}
