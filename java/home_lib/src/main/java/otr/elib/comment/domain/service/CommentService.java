package otr.elib.comment.domain.service;

import otr.elib.comment.domain.entity.Comment;

import java.util.List;

/**
 *
 */
public interface CommentService {

    boolean addComment(Comment comment);

    List<Comment> getLast10Comments();

}
