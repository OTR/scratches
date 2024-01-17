package com.github.otr.home_lib.framework.presentation;

import com.github.otr.home_lib.domain.service.CommentService;
import com.github.otr.home_lib.domain.builder.CommentBuilder;

import com.github.otr.home_lib.framework.di.Component;

import com.github.otr.home_lib.domain.entity.Comment;

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
        this.service = Component.getCommentService();
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
        // TODO
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
