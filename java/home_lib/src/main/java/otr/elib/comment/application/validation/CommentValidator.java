package otr.elib.comment.application.validation;

import otr.elib.comment.domain.builder.CommentBuilder;
import otr.elib.comment.domain.entity.Comment;

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
