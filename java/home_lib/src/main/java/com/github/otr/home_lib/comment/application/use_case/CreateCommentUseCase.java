package com.github.otr.home_lib.comment.application.use_case;

import com.github.otr.home_lib.comment.domain.entity.Comment;
import com.github.otr.home_lib.comment.domain.repository.CommentRepository;

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
