package com.github.otr.home_lib.data;

import com.github.otr.home_lib.domain.entity.Comment;
import com.github.otr.home_lib.domain.repository.CommentRepository;

import java.util.List;

/**
 *
 */
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addComment(Comment comment) {
        return repository.createComment(comment);
    }

    @Override
    public List<Comment> getLast10Comments() {
        return repository.getLast10Comments();
    }

}
