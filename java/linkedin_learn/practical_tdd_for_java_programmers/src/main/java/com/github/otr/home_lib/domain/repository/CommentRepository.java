package com.github.otr.home_lib.domain.repository;

import com.github.otr.home_lib.domain.entity.Comment;

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
