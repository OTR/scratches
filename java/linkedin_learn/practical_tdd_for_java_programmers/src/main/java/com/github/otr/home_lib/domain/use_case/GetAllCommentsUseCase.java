package com.github.otr.home_lib.domain.use_case;

import com.github.otr.home_lib.domain.entity.Comment;
import com.github.otr.home_lib.domain.repository.CommentRepository;

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
