package otr.elib.comment.domain.builder;

import otr.elib.comment.domain.entity.Comment;

import java.time.LocalDateTime;
/**
 *
 */
public class CommentBuilder {

    private static int UNKNOWN_ID = -1;

    /**
     * Creates a new Comment at instant time from the given text
     */
    public static Comment newCommentFrom(String text) {
        return new Comment(
                UNKNOWN_ID,
                getCurrentDateTime(),
                text
        );
    }

    private static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

}
