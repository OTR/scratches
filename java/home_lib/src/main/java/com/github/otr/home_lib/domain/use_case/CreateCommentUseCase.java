package com.github.otr.home_lib.domain.use_case;

import com.github.otr.home_lib.domain.entity.Comment;
import com.github.otr.home_lib.domain.repository.CommentRepository;

/**
 *
 */
public class CreateCommentUseCase {

    private CommentRepository repository;

    public CreateCommentUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public boolean invoke(Comment comment) {
        return repository.createComment(comment);
    }

}
