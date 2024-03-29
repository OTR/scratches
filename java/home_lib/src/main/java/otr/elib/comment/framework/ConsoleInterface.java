package otr.elib.comment.framework;

import otr.elib.comment.domain.service.CommentService;
import otr.elib.comment.domain.builder.CommentBuilder;
import otr.elib.comment.di.Component;
import otr.elib.comment.domain.entity.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class ConsoleInterface {

    private final String INTRO_STRING = "Hello, a new user!\nType `/exit` for exiting";
    private final int MAX_LINE_WIDTH = 80;
    private final String LINE_SEPARATOR = "\n";
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final CommentService service;

    public ConsoleInterface() {
        this.service = Component.getCommentService(); // TODO: Replace with DI
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(getIntroBlock());

        while (true) {

            System.out.print(">>> ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("/exit")) {
                handleExitCommand();
                break;
            }

            processUserInput(userInput);
        }

        scanner.close();
        System.exit(0);
    }

    private String getIntroBlock() {
        return INTRO_STRING;
    }

    private void processUserInput(String userInput) {

        switch (userInput) {
            case "/show": {
                System.out.println(getCommentsBlock());
                break;
            }
            default: {
                Comment newComment = CommentBuilder.newCommentFrom(userInput);
                service.addComment(newComment);
            }

        }
    }

    private void handleExitCommand() {
        // TODO: Implement me
    }

    private String getCommentsBlock() {
        List<Comment> comments = service.getLast10Comments();
        StringBuilder sb = new StringBuilder("Last 10 comments:\n")
                .append("_".repeat(MAX_LINE_WIDTH)).append(LINE_SEPARATOR);
        for (Comment comment : comments) {
            LocalDateTime created = comment.created();
            String verboseCreated = created.format(DATE_TIME_FORMATTER);
            sb.append(verboseCreated).append(LINE_SEPARATOR);
            sb.append(comment.text()).append(LINE_SEPARATOR);
            sb.append("_".repeat(MAX_LINE_WIDTH)).append(LINE_SEPARATOR);
        }

        return sb.toString();
    }

}
