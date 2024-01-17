package com.github.otr.home_lib.framework.di;

import com.github.otr.home_lib.domain.service.CommentService;
import com.github.otr.home_lib.framework.adapter.repository.ApacheDerbyCommentRepositoryImpl;
import com.github.otr.home_lib.framework.adapter.service.CommentServiceImpl;
import com.github.otr.home_lib.framework.persistence.InMemoryListCommentRepositoryImpl;

import com.github.otr.home_lib.domain.repository.CommentRepository;

/**
 *
 */
public class Component {

    // TODO: Read the chosen backend from the Properties file
    private static final String DATABASE_BACKEND = "DERBY";

    public static CommentService getCommentService() {
        CommentRepository repository = getCommentRepository();
        return new CommentServiceImpl(repository);
    }

    public static CommentRepository getCommentRepository() {
        CommentRepository repository = switch (DATABASE_BACKEND) {
            case "DERBY" -> new ApacheDerbyCommentRepositoryImpl();
            case "H2" -> new InMemoryListCommentRepositoryImpl();
            default -> new InMemoryListCommentRepositoryImpl();
        };
        return repository;
    }

}
