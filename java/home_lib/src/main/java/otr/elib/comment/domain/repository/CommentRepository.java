package otr.elib.comment.domain.repository;

import otr.elib.comment.domain.entity.Comment;

import java.util.List;

/**
 * Every concrete repository that works with comments
 * should implement this interface
 */
public interface CommentRepository {

    boolean createComment(Comment comment);

    List<Comment> getAllComments();

    /**
     * @return last 10 comments from data source
     */
    List<Comment> getLast10Comments();

}
