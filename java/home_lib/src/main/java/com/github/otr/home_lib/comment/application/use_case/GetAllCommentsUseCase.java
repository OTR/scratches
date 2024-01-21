package com.github.otr.home_lib.comment.application.use_case;

import com.github.otr.home_lib.comment.domain.entity.Comment;
import com.github.otr.home_lib.comment.domain.repository.CommentRepository;

import java.util.List;

/**
 *
 */
public class GetAllCommentsUseCase {

    private CommentRepository repository;

    public GetAllCommentsUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> invoke() {
        return repository.getAllComments();
    }

}
