package com.github.otr.home_lib.domain.use_case;

import com.github.otr.home_lib.domain.entity.Comment;
import com.github.otr.home_lib.domain.repository.CommentRepository;

import java.util.List;

/**
 *
 */
public class GetLast10CommentsUseCase {

    private CommentRepository repository;

    public GetLast10CommentsUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> invoke() {
        return repository.getLast10Comments();
    }

}
