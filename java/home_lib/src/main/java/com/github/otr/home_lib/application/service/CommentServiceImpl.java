package com.github.otr.home_lib.application.service;

import com.github.otr.home_lib.application.validation.CommentValidator;
import com.github.otr.home_lib.domain.entity.Comment;
import com.github.otr.home_lib.domain.repository.CommentRepository;
import com.github.otr.home_lib.domain.service.CommentService;
import com.github.otr.home_lib.framework.di.Component;

import java.util.List;

/**
 *
 */
public class CommentServiceImpl implements CommentService {

    public static final boolean UNABLE_TO_ADD_COMMENT = false;

    private final CommentRepository repository;
    private final CommentValidator validator;

    public CommentServiceImpl(CommentRepository repository, CommentValidator validator) {
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public boolean addComment(Comment comment) {
        if (validator.isValid(comment)) {
            comment = validator.sanitize(comment);
            return repository.createComment(comment);
        } else {
            return UNABLE_TO_ADD_COMMENT;
        }
    }

    @Override
    public List<Comment> getLast10Comments() {
        return repository.getLast10Comments();
    }

}
