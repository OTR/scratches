package com.github.otr.home_lib.comment.application.validation;

import com.github.otr.home_lib.comment.domain.builder.CommentBuilder;
import com.github.otr.home_lib.comment.domain.entity.Comment;

/**
 *
 */
public class CommentValidator {

    /**
     * The comment is valid when it is at least is not blank
     */
    public static boolean isValid(Comment comment) {
        return isNotBlank(comment);
    }

    private static boolean isNotBlank(Comment comment) {
        return !comment.text().isBlank();
    }

    public static Comment sanitize(Comment comment) {
        // TODO: Implement something, like HTML escaping or
        //  prevent javascript/sql injections
        String sanitizedText = comment.text().strip();
        return CommentBuilder.newCommentFrom(sanitizedText);
    }

}
