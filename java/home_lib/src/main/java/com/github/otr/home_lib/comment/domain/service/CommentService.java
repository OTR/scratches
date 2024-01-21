package com.github.otr.home_lib.comment.domain.service;

import com.github.otr.home_lib.comment.domain.entity.Comment;

import java.util.List;

/**
 *
 */
public interface CommentService {

    boolean addComment(Comment comment);

    List<Comment> getLast10Comments();

}
