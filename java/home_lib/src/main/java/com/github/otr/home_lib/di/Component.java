package com.github.otr.home_lib.di;

import com.github.otr.home_lib.data.CommentService;
import com.github.otr.home_lib.data.CommentServiceImpl;
import com.github.otr.home_lib.data.InMemoryCommentRepository;

import com.github.otr.home_lib.domain.repository.CommentRepository;

/**
 *
 */
public class Component {

    public static CommentService getCommentService() {
        CommentRepository repository = new InMemoryCommentRepository();
        return new CommentServiceImpl(repository);
    }

}
