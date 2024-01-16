package com.github.otr.home_lib.data;

import com.github.otr.home_lib.domain.entity.Comment;

import java.util.List;

/**
 *
 */
public interface CommentService {

    boolean addComment(Comment comment);

    List<Comment> getLast10Comments();

}
