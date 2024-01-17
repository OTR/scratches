package com.github.otr.home_lib.framework.di;

import com.github.otr.home_lib.domain.service.CommentService;
import com.github.otr.home_lib.framework.adapter.service.CommentServiceImpl;
import com.github.otr.home_lib.framework.persistence.InMemoryCommentRepositoryImpl;

import com.github.otr.home_lib.domain.repository.CommentRepository;

/**
 *
 */
public class Component {

    public static CommentService getCommentService() {
        CommentRepository repository = new InMemoryCommentRepositoryImpl();
        return new CommentServiceImpl(repository);
    }

}
